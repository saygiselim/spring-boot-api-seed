package com.saygiselim.springboot.seed.app.sample;

import javax.validation.constraints.NotBlank;

public final class SampleDTO {
    private Integer id;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static SampleDTO fromSample(Sample sample) {
        SampleDTO sampleDTO = new SampleDTO();
        sampleDTO.setId(sample.getId());
        sampleDTO.setTitle(sample.getTitle());
        sampleDTO.setContent(sample.getContent());

        return sampleDTO;
    }

    public Sample toSample() {
        Sample sample = new Sample();
        if (id != null) {
            sample.setId(id);
        }
        sample.setTitle(title);
        sample.setContent(content);

        return sample;
    }
}