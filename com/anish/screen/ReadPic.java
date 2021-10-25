package com.anish.screen;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ReadPic {
    private static int[][] cPic = new int[256][3];

    private int[] getRGB(BufferedImage image, int x, int y) {
        int[] rgb = new int[3];
        int pixel = image.getRGB(x, y);
        rgb[0] = (pixel & 0xff0000) >> 16;
        rgb[1] = (pixel & 0xff00) >> 8;
        rgb[2] = (pixel & 0xff);

        return rgb;
    }
    public ReadPic() throws IOException {
        BufferedImage image = ImageIO.read(new File("c256.png"));
        for(int k=0;k<16;k++) {
            for(int j=0;j<16;j++) {
                for(int i=0;i<3;i++) {
                    cPic[16*k+j][i]=getRGB(image,j*image.getWidth()/16+8,k*image.getHeight()/16+8)[i];
                }
            }
        }
    }

    public int[] getRGB(int i) {
        return cPic[i];
    }
}

