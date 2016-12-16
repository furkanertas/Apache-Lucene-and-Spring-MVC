package com.lucene.mvc.data.services;

import com.lucene.mvc.DB.DBManage;
import com.lucene.mvc.data.entities.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toshiba on 20.7.2016.
 */
public class TextService {
    private Connection db = DBManage.getConnection();
    public List<Text> getResult (String word) {
        long startTime = System.currentTimeMillis();
        List<Text> list = new ArrayList<Text>();
        try {
            PreparedStatement stmt = db.prepareStatement("SELECT * FROM pdf WHERE text LIKE ?");
            stmt.setString(1, '%'+word+'%');
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                list.add(new Text(resultSet.getString(2),resultSet.getInt(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        double seconds =  ((double)(endTime-startTime) / 1000.0) % 60.0 ;
        list.add(new Text(String.valueOf(seconds), 0));
        return list;
    }

    public List<Text> getAllData () {
        List<Text> list = new ArrayList<Text>();
        try {
            PreparedStatement stmt = db.prepareStatement("SELECT * FROM pdf");
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                list.add(new Text(resultSet.getString(2),resultSet.getInt(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
