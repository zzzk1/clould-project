package com.example.mallproject.config;

import feign.form.spring.SpringFormEncoder;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.ContentType;
import feign.form.FormEncoder;
import feign.form.MultipartFormContentProcessor;
import feign.form.spring.SpringManyMultipartFilesWriter;
import feign.form.spring.SpringSingleMultipartFileWriter;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class FeignSpringFormEncoder extends FormEncoder {

    public FeignSpringFormEncoder() {
        this(new Default());
    }

    public FeignSpringFormEncoder(Encoder delegate) {
        super(delegate);
        MultipartFormContentProcessor processor = (MultipartFormContentProcessor) this.getContentProcessor(ContentType.MULTIPART);
        processor.addFirstWriter(new SpringSingleMultipartFileWriter());
        processor.addFirstWriter(new SpringManyMultipartFilesWriter());
    }

    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        HashMap data;
        if (bodyType.equals(MultipartFile[].class)) {
            MultipartFile[] files = (MultipartFile[]) object;
            data = new HashMap();
            //data添加数组
            if (files != null) {
                data.put(files.length == 0 ? "" : files[0].getName(), files);
            }
            super.encode(data, MAP_STRING_WILDCARD, template);
        } else if (bodyType.equals(MultipartFile.class)) {
            MultipartFile file = (MultipartFile) object;
            data = (HashMap) Collections.singletonMap(file.getName(), object);
            super.encode(data, MAP_STRING_WILDCARD, template);
        } else if (this.isMultipartFileCollection(object)) {
            Iterable<?> iterable = (Iterable) object;
            data = new HashMap();
            Iterator var13 = iterable.iterator();

            while (var13.hasNext()) {
                Object item = var13.next();
                MultipartFile file = (MultipartFile) item;
                data.put(file.getName(), file);
            }
        } else {
            super.encode(object, bodyType, template);
        }

    }

    private boolean isMultipartFileCollection(Object object) {
        if (!(object instanceof Iterable)) {
            return false;
        } else {
            Iterable<?> iterable = (Iterable) object;
            Iterator<?> iterator = iterable.iterator();
            return iterator.hasNext() && iterator.next() instanceof MultipartFile;
        }
    }
}
