package serialize;

import java.io.File;

public interface Serializable {
	public boolean serailizeContent(File file, String content) throws Exception;
}
