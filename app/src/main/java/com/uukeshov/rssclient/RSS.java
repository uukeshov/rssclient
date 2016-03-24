package com.uukeshov.rssclient;

/**
 * Created by uukeshov on 3/19/2016.
 */
public class RSS {

    String _newsLink; //link to image
    String _newsPubDate;
    String _newsLinktoImage; //link to news
    String _newsTitle;
    String _newsDescr;
    int _newsId;

    public RSS() {
    }

    public String get_newsLinktoImage() {
        return _newsLinktoImage;
    }

    public void set_newsLinktoImage(String newsLinktoImage) {
        this._newsLinktoImage = newsLinktoImage;
    }

    public String get_newsLink() {
        return _newsLink;
    }

    public void set_newsLink(String newsLink) {
        this._newsLink = newsLink;
    }

    public String get_newsPubDate() {
        return _newsPubDate;
    }

    public void set_newsPubDate(String newsPubDate) {

        /*Calendar c = Calendar.getInstance();
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formateDate = dt.format(newsPubDate);*/
        this._newsPubDate = newsPubDate;
    }

    public String get_newsTitle() {
        return _newsTitle;
    }

    public void set_newsTitle(String newsTitle) {
        this._newsTitle = newsTitle;
    }

    public int get_newsId() {
        return _newsId;
    }

    public void set_newsId(int newsId) {
        this._newsId = newsId;
    }

    public String get_newsDescr() {
        return _newsDescr;
    }

    public void set_newsDescr(String _newsDescr) {
        this._newsDescr = _newsDescr;
    }

    public RSS(String newsTitle, String newsLink, String newsPubDate, String newsDescr, String newsLinktoImage) {
        this._newsTitle = newsTitle;
        this._newsLink = newsLink;
        this._newsPubDate = newsPubDate;
        this._newsDescr = newsDescr;
        this._newsLinktoImage = newsLinktoImage;
    }

    @Override
    public String toString() {
        return "RSS{" +
                ", _newsTitle='" + _newsTitle + '\'' +
                ", _newsLink='" + _newsLink + '\'' +
                ", _newsPubDate='" + _newsPubDate + '\'' +
                ", _newsDescr='" + _newsDescr + '\'' +
                ", _newsLinktoImage='" + _newsLinktoImage + '\'' +
                ", _newsId=" + _newsId +
                '}';
    }
}
