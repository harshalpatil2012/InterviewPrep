package codeInterView.desPattern.factory;

interface ImageReader {
    DecodedImage getDecodeImage();
}

class DecodedImage {
    private String image;

    public DecodedImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return image + ": is decoded";
    }
}

class GifReader implements ImageReader {
    private DecodedImage decodedImage;

    public GifReader(String image) {
        this.decodedImage = new DecodedImage(image);
    }

    @Override
    public DecodedImage getDecodeImage() {
        return decodedImage;
    }
}

class JpegReader implements ImageReader {
    private DecodedImage decodedImage;

    public JpegReader(String image) {
        decodedImage = new DecodedImage(image);
    }

    @Override
    public DecodedImage getDecodeImage() {
        return decodedImage;
    }
}

class ImageDecodeeFactory {
    ImageReader reader = null;
    DecodedImage decodedImage;

    public DecodedImage getDecodedImage(String format, String image) {
        if (format == null) {
            return null;
        }
        if (format.equals("gif")) {
            reader = new GifReader(image);
        }
        if (format.equals("jpeg")) {
            reader = new JpegReader(image);
        }
        decodedImage = reader.getDecodeImage();
        return decodedImage;
    }
}

public class FactoryMethodDemo {
    public static void main(String[] args) {

        String image = "jpeg";
        String format = "jpeg";
        ImageDecodeeFactory fact = new ImageDecodeeFactory();
        DecodedImage decodedImage = fact.getDecodedImage(format, image);
        System.out.println(decodedImage);
    }
}
