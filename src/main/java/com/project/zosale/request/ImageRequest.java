package com.project.zosale.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageRequest {
    private String name ;
    private String contentType;
    private byte[] data;
}
