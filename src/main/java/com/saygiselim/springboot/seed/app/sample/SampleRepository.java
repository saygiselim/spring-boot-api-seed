package com.saygiselim.springboot.seed.app.sample;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SampleRepository extends CrudRepository<Sample, Integer> {
    List<Sample> findAll();
}