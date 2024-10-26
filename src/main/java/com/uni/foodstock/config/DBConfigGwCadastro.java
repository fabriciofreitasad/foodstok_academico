package com.uni.foodstock.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.uni.foodstock.repositories"},
        entityManagerFactoryRef = "foodStockEntityManagerFactory",
        transactionManagerRef = "foodStockTransactionManager"
)
public class DBConfigGwCadastro {

    @Bean(name = "foodStockDataSource")
    public DataSource foodStockDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/foodstock?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
        config.setUsername("root");
        config.setPassword("admin");
        config.setMaximumPoolSize(10);

        return new HikariDataSource(config);
    }

    @Bean(name = "foodStockEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean foodStockEntityManagerFactory(
            @Qualifier("foodStockDataSource") DataSource foodStockDataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(foodStockDataSource);
        em.setPackagesToScan("com.uni.foodstock.entities"); // Ajuste para o pacote das suas entidades

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.SHOW_SQL, true);
        properties.put(Environment.FORMAT_SQL, true);

        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean(name = "foodStockTransactionManager")
    public PlatformTransactionManager foodStockTransactionManager(
            @Qualifier("foodStockEntityManagerFactory") LocalContainerEntityManagerFactoryBean foodStockEntityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(foodStockEntityManagerFactory.getObject());
        return transactionManager;
    }
}

