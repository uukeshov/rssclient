package com.uukeshov.rssclient;

/**
 * Created by uukeshov on 3/19/2016.
 */
public class RSS {

    String _newstitle;
    String _newslink;
    String _newspubDate;
    String _newsenclosure;
    String _newstext;
    int _newsId;

    public int get_newsId() {
        return _newsId;
    }

    public void set_newsId(int _newsId) {
        this._newsId = _newsId;
    }

    public String get_newstitle() {
        return _newstitle;
    }

    public void set_newstitle(String _newstitle) {
        this._newstitle = _newstitle;
    }

    public String get_newslink() {
        return _newslink;
    }

    public void set_newslink(String _newslink) {
        this._newslink = _newslink;
    }

    public String get_newspubDate() {
        return _newspubDate;
    }

    public void set_newspubDate(String _newspubDate) {
        this._newspubDate = _newspubDate;
    }

    public String get_newsenclosure() {
        return _newsenclosure;
    }

    public void set_newsenclosure(String _newsenclosure) {
        this._newsenclosure = _newsenclosure;
    }

    public String get_newstext() {
        return _newstext;
    }

    public void set_newstext(String _newstext) {
        this._newstext = _newstext;
    }

    public RSS() {
    }

    public RSS(int _newsId,String _newstitle, String _newslink, String _newspubDate, String _newsenclosure, String _newstext) {
        this._newstitle = _newstitle;
        this._newslink = _newslink;
        this._newspubDate = _newspubDate;
        this._newsenclosure = _newsenclosure;
        this._newstext = _newstext;
        this._newsId = _newsId;
    }

    @Override
    public String toString() {
        return "RSS{" +
                "_newstitle='" + _newstitle + '\'' +
                ", _newslink='" + _newslink + '\'' +
                ", _newspubDate='" + _newspubDate + '\'' +
                ", _newsenclosure='" + _newsenclosure + '\'' +
                ", _newstext='" + _newstext + '\'' +
                ", _newsId='" + _newsId + '\'' +
                '}';
    }
}
