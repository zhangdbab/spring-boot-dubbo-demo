package com.sans.base.service;

import com.sans.base.dto.ProviderTestDTO;
import java.util.List;


public interface IUserService {
    List<ProviderTestDTO> queryList();
}