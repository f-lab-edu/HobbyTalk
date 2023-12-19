package com.jongho.common.dao;

import com.jongho.common.config.MyBatisConfig;
import com.jongho.common.config.DataSourceConfig;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@Import({MyBatisConfig.class, DataSourceConfig.class})
@TestConfiguration
public class RepositoryTestConfiguration {
}
