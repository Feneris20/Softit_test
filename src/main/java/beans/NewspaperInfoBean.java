package beans;

/**
 * Created by ADOBNER on 2018-08-11.
 */

import model.NewspaperInfo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class NewspaperInfoBean implements Serializable {

    private static final long serialVersionUID = 6081417964063918994L;

    public String DRIVER = "com.mysql.jdbc.Driver";
    public String DB_URL = "jdbc:mysql://localhost:3306/Mysql";
    private String USERNAME = "admin";
    private String PASSWORD = "admin";
    private Connection conn;
    private Statement stat;

    public NewspaperInfoBean(){

        try {

            Class.forName(DRIVER);

            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stat = conn.createStatement();

            stat.execute("CREATE TABLE IF NOT EXISTS newspaper(\n" +
                    "file_name VARCHAR(500),\n" +
                    "cname VARCHAR(50),\n" +
                    "width INTEGER,\n" +
                    "height INTEGER,\n" +
                    "dpi INTEGER,\n" +
                    "cdate DATE)");

        } catch (SQLException ex) {
            System.out.println("in exec");
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void insertNewspaperInfo(NewspaperInfo newspaperInfo) throws SQLException {

            PreparedStatement prepStmt = conn.prepareStatement(
                    "INSERT INTO newspaper VALUES (?, ?, ?, ?, ?, ?);");
            prepStmt.setString(1, newspaperInfo.getFileName());
            prepStmt.setString(2, newspaperInfo.getName());
            prepStmt.setInt(3, newspaperInfo.getWidth());
            prepStmt.setInt(4, newspaperInfo.getHeight());
            prepStmt.setInt(5, newspaperInfo.getDpi());
            prepStmt.setDate(6, Date.valueOf(String.valueOf(newspaperInfo.getDate())));
            prepStmt.execute();
            prepStmt.close();

    }

    public List<NewspaperInfo> getNewspaperInfos() throws ClassNotFoundException, SQLException {

        List<NewspaperInfo> newspaperInfos = new ArrayList<NewspaperInfo>();
        PreparedStatement pstmt = conn
                .prepareStatement("select file_name, cname, width, height, dpi, cdate from newspaper");
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {

            NewspaperInfo newspaperInfo = new NewspaperInfo();
            newspaperInfo.setFileName(rs.getString("file_name"));
            newspaperInfo.setName(rs.getString("cname"));
            newspaperInfo.setWidth(rs.getInt("width"));
            newspaperInfo.setHeight(rs.getInt("height"));
            newspaperInfo.setDpi(rs.getInt("dpi"));
            newspaperInfo.setDate(rs.getDate("cdate"));

            newspaperInfos.add(newspaperInfo);

        }
        rs.close();
        pstmt.close();

        return newspaperInfos;

    }


}