package samples.job.shortenerurl.service;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;

@Service

public class ServiceEncrypt {

    public static String generateCRC32Hash(String input){
        CRC32 crc32 = new CRC32();
        crc32.update(input.getBytes(StandardCharsets.UTF_8));
        return Long.toHexString(crc32.getValue());
    }
}