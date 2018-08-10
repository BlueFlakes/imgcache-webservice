package imgcache.endpoints;

import imgcache.utils.IgniteClientConnector;
import org.apache.ignite.IgniteCache;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/image-resource/")
public class ImageEndPoint {

    @GetMapping("{identity}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable Long identity) {
        HttpHeaders headers = new HttpHeaders();
        addImageHeaders(headers);

        ByteArrayResource image = IgniteClientConnector.apply(ignite -> {
            IgniteCache<Long, byte[]> imgStore = ignite.getOrCreateCache(Caches.IMG_STORE.name());
            byte[] img = imgStore.get(identity);

            return new ByteArrayResource(img);
        });

        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<byte[]> storeImage(@RequestBody byte[] image, @RequestHeader("identity") Long identity) {
        if (identity == null) {
            return ResponseEntity.badRequest()
                                 .build();
        }

        HttpHeaders headers = new HttpHeaders();
        addImageHeaders(headers);

        IgniteClientConnector.accept(ignite -> {
            IgniteCache<Long, byte[]> imgCache = ignite.getOrCreateCache(Caches.IMG_STORE.name());
            imgCache.put(identity, image);
        });

        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

    private void addImageHeaders(HttpHeaders httpHeaders) {
        httpHeaders.put("Content-Type", Collections.singletonList("image/png"));
    }
}
