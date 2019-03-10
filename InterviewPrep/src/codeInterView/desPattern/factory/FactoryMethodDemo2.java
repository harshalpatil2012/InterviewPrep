package codeInterView.desPattern.factory;

interface ImageReader2 {
    String getDecodeImage();
}

class GifReader2 implements ImageReader2 {
    private String decodedImage;

    public GifReader2(String image) {
        this.decodedImage = new String(image);
    }

    @Override
    public String getDecodeImage() {
        return decodedImage;
    }
}

class JpegReader2 implements ImageReader2 {
    private String decodedImage;

    public JpegReader2(String image) {
        decodedImage = new String(image);
    }

    @Override
    public String getDecodeImage() {
        return decodedImage;
    }
}

class ImageDecodeeFactory2 {
    ImageReader2 reader = null;
    String decodedImage;

    public String getDecodedImage(String format, String image) {
        if (format == null) {
            return null;
        }
        if (format.equals("gif")) {
            reader = new GifReader2(image);
        }
        if (format.equals("jpeg")) {
            reader = new JpegReader2(image);
        }
        decodedImage = reader.getDecodeImage();
        return decodedImage;
    }
}

public class FactoryMethodDemo2 {
    public static void main(String[] args) {

        String image = "jpeg";
        String format = "jpeg";
        ImageDecodeeFactory2 fact = new ImageDecodeeFactory2();
        String decodedImage = fact.getDecodedImage(format, image);
        System.out.println(decodedImage);
    }
}
