function gl_init(canvas) {
  canvas.gl = canvas.getContext('webgl') || canvas.getContext('experimental-webgl');
  var gl = canvas.gl;
  gl.clearColor(0.0, 0.0, 0.0, 1.0);  // Clear to black, fully opaque
  gl.clearDepth(1.0);                 // Clear everything
  gl.enable(gl.DEPTH_TEST);           // Enable depth testing
  gl.depthFunc(gl.LEQUAL);            // Near things obscure far things
  gl.viewport(0,0,canvas.width,canvas.height);  //set viewport
  canvas.buffers = [];
  canvas.objects = [];
  canvas.matrix = [];
  canvas.attribs = [];
  canvas.uniforms = [];
  canvas.render = [];  //render functions r_...
  canvas.args = [];  //render arguments
  setInterval(gl_render, 16, canvas);
}

function gl_render(canvas) {
  var gl = canvas.gl;
  gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);
  console.log('render:' + gl);
  var idx = 0;
  while (true) {
    var func = canvas.render[idx];
    var args = canvas.args[idx];
    if (!func) return;
    func(canvas, gl, args);
    idx++;
  }
}

function gl_message(msg, canvas) {
  var gl = canvas.gl;
  switch (msg.event) {
    case "loadvs":
      canvas.vs = gl.createShader(gl.VERTEX_SHADER);
      gl.shaderSource(canvas.vs, msg.src);
      gl.compileShader(canvas.vs);
      if (!gl.getShaderParameter(canvas.vs, gl.COMPILE_STATUS)) {
        console.log("An error occurred compiling the shader: " + gl.getShaderInfoLog(canvas.vs));
      }
      break;
    case "loadfs":
      canvas.fs = gl.createShader(gl.FRAGMENT_SHADER);
      gl.shaderSource(canvas.fs, msg.src);
      gl.compileShader(canvas.fs);
      if (!gl.getShaderParameter(canvas.fs, gl.COMPILE_STATUS)) {
        console.log("An error occurred compiling the shader: " + gl.getShaderInfoLog(canvas.fs));
      }
      break;
    case "link":
      canvas.program = gl.createProgram();
      gl.attachShader(canvas.program, canvas.vs);
      gl.attachShader(canvas.program, canvas.fs);
      gl.linkProgram(canvas.program);
      if (!gl.getProgramParameter(canvas.program, gl.LINK_STATUS)) {
        console.log("Unable to initialize the shader program: " + gl.getProgramInfoLog(canvas.program));
      }
      gl.useProgram(canvas.program);
      break;
    case "buffer":
      var buffer = gl.createBuffer();
      gl.bindBuffer(gl.ARRAY_BUFFER, buffer);
      gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(bindata,0), gl.STATIC_DRAW);
      canvas.buffers[msg.idx] = buffer;
      break;
    case "matrix":
      canvas.matrix[msg.idx] = new Float32Array(bindata,0);
      break;
    case "getuniform":
      var uniform = gl.getUniformLocation(canvas.program, msg.name);
      canvas.uniforms[msg.idx] = uniform;
      break;
    case "getattrib":
      var attrib = gl.getAttribLocation(canvas.program, msg.name);
      gl.enableVertexAttribArray(attrib);
      canvas.attribs[msg.idx] = attrib;
      break;
    case "r_matrix":
      var idx = msg.idx;
      canvas.render[idx] = r_matrix;
      canvas.args[idx] = {uidx:msg.uidx, midx:msg.midx};
      break;
    case "r_attrib":
      var idx = msg.idx;
      canvas.render[idx] = r_attrib;
      canvas.args[idx] = {aidx:msg.aidx, bufidx:msg.bufidx, cnt:msg.cnt};
      break;
    case "r_drawArrays":
      var idx = msg.idx;
      canvas.render[idx] = r_drawArrays;
      canvas.args[idx] = {type:msg.type, cnt:msg.cnt};
      break;
  }
}

//rendering functions

function r_matrix(canvas, gl, args) {
  gl.uniformMatrix4fv(canvas.uniforms[args.uidx], false, canvas.matrix[args.midx]);
}

function r_attrib(canvas, gl, args) {
  gl.bindBuffer(gl.ARRAY_BUFFER, canvas.buffers[args.bufidx]);
  gl.vertexAttribPointer(canvas.attribs[args.aidx], args.cnt, gl.FLOAT, false, 0, 0);
}

function r_drawArrays(canvas, gl, args) {
  gl.drawArrays(args.type, 0, args.cnt);
}
