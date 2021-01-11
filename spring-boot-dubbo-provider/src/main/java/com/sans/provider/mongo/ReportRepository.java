package com.sans.provider.mongo;

import com.sans.base.dto.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportRepository   extends MongoRepository<User, String> {
}
