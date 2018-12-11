package Model.DataBaseCommunication;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersPicturesTableManager extends ATableManager {

    public void CreateUsersProfileImage(String username, File imageFile) {
        byte[] image = extractBytes(imageFile);

        connect(); //connect to database

        //create user - sql command
        String sql = "INSERT INTO usersPictures(username,picture) VALUES(?,?)";

        //try to create users picture
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setBytes(2, image);
            pstmt.executeUpdate();
        }
        catch (SQLException e){
            //closeConnection(); //close connection to database
            e.printStackTrace();
        }
        closeConnection(); //close connection
    }

    public Image ReadUsersProfileImage(String username) {
        connect(); //connect to database

        //sql commend
        String sql = "SELECT username, picture FROM usersPictures WHERE username = ?";

        //read users picture
        byte[] picturesBytes = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the value
            pstmt.setString(1, username);
            //
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                picturesBytes = rs.getBytes("picture");
                break;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection(); //disconnect from database

        Image image = null;
        if(picturesBytes != null)
            image = createImage(picturesBytes);

        return image;
    }

    private Image createImage(byte[] picturesBytes) {
        Image image = null;
        try {
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(picturesBytes));
            image = SwingFXUtils.toFXImage(img, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void UpdateUsersProfileImage(String username, File imageFile) {
        byte[] image = extractBytes(imageFile);

        connect(); //Connect to database
        //SQL commend
        String sql = "UPDATE usersPictures SET picture = ? "
                + "WHERE username = ?";

        try {
            //Run sql commend
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setBytes(1, image);
            pstmt.setString(2, username);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(); //disconnect from database
    }

    private byte[] extractBytes (File imageFile) {
        // open image
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get DataBufferBytes from Raster
        WritableRaster raster = bufferedImage .getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

        return data.getData();
    }
}