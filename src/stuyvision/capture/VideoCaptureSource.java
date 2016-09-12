package stuyvision.capture;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import stuyvision.util.FileManager;

public class VideoCaptureSource extends CaptureSource {

    private final String filename;
    private VideoCapture capture = null;

    public VideoCaptureSource(String filename) {
        FileManager.assertFileExists(filename);
        this.filename = filename;
        reinitializeCaptureSource();
    }

    public VideoCaptureSource(String filename, CaptureSource.ResizeDimension dim, int length) {
        this(filename);
        resizeDimensionTo(dim, length);
    }

    @Override
    public void reinitializeCaptureSource() {
        if (capture != null) {
            capture.release();
        }
        capture = new VideoCapture(filename);
    }

    @Override
    public boolean isOpened() {
        return capture.isOpened();
    }

    @Override
    public boolean readFrame(Mat mat) {
        return capture.read(mat);
    }

}
