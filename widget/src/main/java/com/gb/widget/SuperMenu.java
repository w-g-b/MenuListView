package com.gb.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.XmlResourceParser;
import android.view.MenuInflater;

import com.gb.widget.constant.XMLConstants;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import androidx.appcompat.view.menu.MenuBuilder;

/**
 * Create by wgb on 2019/4/17.
 */
@SuppressLint("RestrictedApi")
public class SuperMenu {
    private ArrayList<SuperMenuItem> mItems;
    private MenuBuilder mMenu;
    private Context mContext;

    public SuperMenu(Context context, int menuRes) {
        mContext = context;
        mMenu = new MenuBuilder(context);
        new MenuInflater(context).inflate(menuRes, mMenu);
        mItems = inflate(menuRes);
    }

    private ArrayList<SuperMenuItem> inflate(int menuRes) {
        ArrayList<SuperMenuItem> items = new ArrayList<>();
        try {
            XmlResourceParser parser = mContext.getResources().getLayout(menuRes);
            int eventType = parser.getEventType();
            int count = 0;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG: {
                        if (parser.getName().equals("item")) {
                            SuperMenuItem item = new SuperMenuItem(mMenu.getItem(count));
                            item.setDes(parser.getAttributeValue(XMLConstants.RES_AUTO_NS, "mActivity"));
                            item.setOnClickFunc(parser.getAttributeValue(XMLConstants.RES_AUTO_NS, "mOnClick"));
                            items.add(item);
                        }
                    }
                    break;
                    case XmlPullParser.END_TAG: {
                        if (parser.getName().equals("item")) {
                            count++;
                        }
                    }
                    break;
                }
                System.out.println(nodeName);
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    public SuperMenuItem getItem(int position) {
        return mItems.get(position);
    }

    public int size() {
        return mItems.size();
    }


}
