package com.saygiselim.springboot.seed.api;

import com.saygiselim.springboot.seed.app.sample.SampleDTO;
import com.saygiselim.springboot.seed.app.sample.SampleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/samples")
@Api("Sample")
public class SampleController {
    private final SampleService sampleService;

    @Autowired
    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @PostMapping()
    @ApiOperation(value = "Creates new sample")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSample(@Valid @RequestBody SampleDTO sampleDTO) {
        sampleService.createSample(sampleDTO.toSample());
    }

    @GetMapping()
    @ApiOperation(value = "Lists all samples")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<SampleDTO> getSampleDTOs() {
        return sampleService.getSampleDTOs();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Returns a sample by given id")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public SampleDTO getSampleDTO(@PathVariable int id) {
        return sampleService.getSampleDTO(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updates a sample by given id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSample(@PathVariable int id, @Valid @RequestBody SampleDTO sampleDTO) {
        sampleService.updateSample(id, sampleDTO);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes a sample by given id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSample(@PathVariable int id) {
        sampleService.deleteSample(id);
    }
}