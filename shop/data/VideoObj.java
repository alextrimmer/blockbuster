package shop.data;

/**
 * Implementation of Video interface.
 * @see Data
 */
final class VideoObj implements Video {
  private final String _title;
  private final int    _year;
  private final String _director;

  /**
   * Initialize all object attributes.
   * Title and director are "trimmed" to remove leading and final space.
   * @throws IllegalArgumentException if object invariant violated.
   */
  VideoObj(String title, int year, String director) {
    _title = title;
    _director = director;
    _year = year;
  }

  public String director() {
    return _director;
  }

  public String title() {
    return _title;
  }

  public int year() {
    return _year;
  }

  public boolean equals(Object thatObject) {
    if (!(thatObject instanceof Video)) {
      return false;
    }
    Video newV = (Video) thatObject;
    return (this._director.equals(newV.director())
            && this._title.equals(newV.title()) &&
            this._year == newV.year());
  }

  public int hashCode() {
    int result = 17;
    int v = 37;
    result = v * result + _title.hashCode();
    result = v * result + _year;
    result = v * result + _director.hashCode();
    return result;
  }

  public int compareTo(Object thatObject) {

    if (thatObject.equals(null)) {
      throw new NullPointerException();
    }
    if (!(thatObject instanceof Video)) {
      throw new IllegalArgumentException();
    }

    VideoObj that = (VideoObj) thatObject;

    int tTest = _title.compareTo(that._title);
    if (tTest != 0) {
      return tTest;
    }
    int yTest = _year - that._year;
    if (yTest != 0) {
      return yTest;
    }
    int dTest = _director.compareTo(that._director);
    if (dTest != 0) {
      return dTest;
    }
    return 0;
  }

  public String toString() {
    return _title + " (" + _year + ") : " + _director;
  }
}
