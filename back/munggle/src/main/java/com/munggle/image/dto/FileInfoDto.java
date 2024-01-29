package com.munggle.image.dto;

import lombok.*;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileInfoDto {

    private String fileName;

    private String fileURL;

}
