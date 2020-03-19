package com.newler.expandablerecyclerview.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/**
 * The backing data object for an {@link ExpandableGroup}
 */
public class ExpandableGroup<T extends Parcelable> implements Parcelable {
  private String title;
  private List<T> items;
  private boolean expand;

  public ExpandableGroup(String title, List<T> items, boolean expand) {
    this.title = title;
    this.items = items;
    this.expand = expand;
  }

  public ExpandableGroup(String title, List<T> items) {
    this.title = title;
    this.items = items;
  }

  public String getTitle() {
    return title;
  }

  public List<T> getItems() {
    return items;
  }

  public int getItemCount() {
    return items == null ? 0 : items.size();
  }


  protected ExpandableGroup(Parcel in) {
    title = in.readString();
    expand = in.readByte() != 0;
    byte hasItems = in.readByte();
    int size = in.readInt();
    if (hasItems == 0x01) {
      items = new ArrayList<T>(size);
      Class<?> type = (Class<?>) in.readSerializable();
      in.readList(items, type.getClassLoader());
    } else {
      items = null;
    }
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(title);
    dest.writeByte(expand ? (byte) 1 : (byte) 0);
    if (items == null) {
      dest.writeByte((byte) (0x00));
      dest.writeInt(0);
    } else {
      dest.writeByte((byte) (0x01));
      dest.writeInt(items.size());
      final Class<?> objectsType = items.get(0).getClass();
      dest.writeSerializable(objectsType);
      dest.writeList(items);
    }
  }

  @SuppressWarnings("unused")
  public static final Creator<ExpandableGroup> CREATOR =
      new Creator<ExpandableGroup>() {
        @Override
        public ExpandableGroup createFromParcel(Parcel in) {
          return new ExpandableGroup(in);
        }

        @Override
        public ExpandableGroup[] newArray(int size) {
          return new ExpandableGroup[size];
        }
      };

  @Override
  public String toString() {
    return "ExpandableGroup{" +
            "title='" + title + '\'' +
            ", items=" + items +
            ", expand=" + expand +
            '}';
  }

  public boolean isExpand() {
    return expand;
  }

  public void setExpand(boolean expand) {
    this.expand = expand;
  }
}
