package com.saygiselim.springboot.seed.app.sample;

import com.saygiselim.springboot.seed.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public final class SampleService {
    private final SampleRepository sampleRepository;

    @Autowired
    public SampleService(SampleRepository sampleRepository) {
        this.sampleRepository = sampleRepository;
    }

    public void createSample(Sample sample) {
        sampleRepository.save(sample);
    }

    public void updateSample(int id, SampleDTO sampleDTO) {
        Sample sample = getSample(id);
        sample.setTitle(sampleDTO.getTitle());
        sample.setContent(sampleDTO.getContent());

        sampleRepository.save(sample);
    }

    public List<Sample> getSamples() {
        return sampleRepository.findAll();
    }

    public List<SampleDTO> getSampleDTOs() {
        return getSamples().stream().map(SampleDTO::fromSample).collect(Collectors.toList());
    }

    public Sample getSample(int id) {
        return sampleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find any sample with id: " + id));
    }

    public SampleDTO getSampleDTO(int id) {
        return SampleDTO.fromSample(getSample(id));
    }

    public void deleteSample(int id) {
        sampleRepository.deleteById(id);
    }
}