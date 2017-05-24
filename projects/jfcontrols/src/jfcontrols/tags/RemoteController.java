package jfcontrols.tags;

/** Remote Controller
 *
 * @author pquiring
 */

import java.util.*;

import javaforce.controls.*;

public class RemoteController {
  private int cid;
  private Object lock = new Object();
  private javaforce.controls.Tag first;
  private HashMap<Tag, javaforce.controls.Tag> map = new HashMap<>();
  private int delay;
  private String ip;
  private int controllerType;

  public RemoteController(int cid, int type, String ip, int speed) {
    this.cid = cid;
    this.controllerType = type;
    this.ip = ip;
    switch (speed) {
      case 1: delay = 1000; break;
      case 0:  //auto -> 100ms
      case 2: delay = 100; break;
      case 3: delay = 10; break;
    }
  }

  public void cancel() {
    synchronized(lock) {
      javaforce.controls.Tag tags[] = map.values().toArray(new javaforce.controls.Tag[0]);
      for(int a=0;a<tags.length;a++) {
        tags[a].stop();
      }
      map.clear();
      first = null;
    }
  }

  public javaforce.controls.Tag getTag(String name, int tagType) {
    synchronized(lock) {
      javaforce.controls.Tag tag = map.get(name);
      if (tag == null) {
        tag = new javaforce.controls.Tag();
        tag.delay = delay;
        tag.host = ip;
        switch (tagType) {
          case TagType.BIT: tag.size = Controller.sizes.bit; break;
          case TagType.BYTE: tag.size = Controller.sizes.int8; break;
          case TagType.SHORT: tag.size = Controller.sizes.int16; break;
          case TagType.INT: tag.size = Controller.sizes.int32; break;
//          case TagType.LONG: tag.size = Controller.sizes.int64; break;
          case TagType.FLOAT: tag.size = Controller.sizes.float32; break;
          case TagType.DOUBLE: tag.size = Controller.sizes.float64; break;
        }
        tag.size = Controller.sizes.int16;  //TODO
        tag.tag = name;
        switch (controllerType) {
//          case 0: tag.type = Controller.types.JFC; break;
          case 1: tag.type = Controller.types.S7; break;
          case 2: tag.type = Controller.types.AB; break;
          case 3: tag.type = Controller.types.MB; break;
          case 4: tag.type = Controller.types.NI; break;
        }
        if (map.size() == 0 || tagType > 1) {
          tag.start();
          first = tag;
        } else {
          //only JFC and S7 support multi-read
          tag.start(first);
        }
      }
      return tag;
    }
  }
}
