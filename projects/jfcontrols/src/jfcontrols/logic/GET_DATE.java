package jfcontrols.logic;

/** Get Date
 *
 * @author pquiring
 */

import javaforce.controls.*;

import jfcontrols.tags.*;

public class GET_DATE extends Logic {

  public boolean isBlock() {
    return true;
  }

  public String getDesc() {
    return "GetDate";
  }

  public String getCode(int[] types, boolean[] array, boolean[] unsigned) {
    return "getdate(tags[1]);";
  }

  public int getTagsCount() {
    return 1;
  }

  public int getTagType(int idx) {
    return IDs.uid_date;
  }
}