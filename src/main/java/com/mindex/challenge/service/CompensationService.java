package com.mindex.challenge.service;

import com.mindex.challenge.data.*;

public interface CompensationService {
    Compensation create(Compensation compensation);
    Compensation read(String id);
}
