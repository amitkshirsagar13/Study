package com.mp3editor.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImageHelper {

	public static BufferedImage getRenderedImage(Image in) {
		int w = in.getWidth(null);
		int h = in.getHeight(null);
		int type = BufferedImage.TYPE_INT_RGB;
		BufferedImage out = new BufferedImage(w, h, type);
		Graphics2D g2 = out.createGraphics();
		g2.drawImage(in, 0, 0, null);
		g2.dispose();
		return out;
	}

	public static Image iconToImage(Icon icon) {
		if (icon instanceof ImageIcon) {
			return ((ImageIcon) icon).getImage();
		} else {
			BufferedImage image = new BufferedImage(icon.getIconWidth(),
					icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
			icon.paintIcon(null, image.getGraphics(), 0, 0);
			return image;
		}
	}

}
