package com.formacionspring.examplebatch2.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.RowMapper;

import com.formacionspring.examplebatch2.model.Cliente;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private DataSource dataSource;
	
	
	@Bean
	public JdbcCursorItemReader<Cliente> reader(){
		JdbcCursorItemReader<Cliente> reader = new JdbcCursorItemReader<Cliente>();
		reader.setDataSource(dataSource);
		reader.setSql("select id,nombre,apellido,email from personas");
		reader.setRowMapper(new RowMapper<Cliente>() {

			@Override
			public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
				Cliente s = new Cliente();
				s.setId(rs.getInt("id"));
				s.setNombre(rs.getString("nombre"));
				s.setApellido(rs.getString("apellido"));
				s.setEmail(rs.getString("email"));
						
				return s;
			}
			
		});
		
		return reader;
	}
	
	@Bean
	public FlatFileItemWriter<Cliente> writer(){
		FlatFileItemWriter<Cliente> writer = new FlatFileItemWriter<Cliente>();
		writer.setResource(new FileSystemResource("C:\\Users\\dell\\Desktop\\cliente.csv"));
		DelimitedLineAggregator<Cliente> aggregator = new DelimitedLineAggregator<>();
		BeanWrapperFieldExtractor<Cliente> fieldExtractor= new BeanWrapperFieldExtractor<>();
		fieldExtractor.setNames(new String[] {"id","nombre","apellido","email"});
		aggregator.setFieldExtractor(fieldExtractor);
		writer.setLineAggregator(aggregator);
		
		return writer;
		
	}
	
	
	@Bean
	public Step executeStep() {
		return stepBuilderFactory.get("executeStep").<Cliente,Cliente>chunk(10).reader(reader()).writer(writer()).build();
	}
	
	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob").incrementer(new RunIdIncrementer()).flow(executeStep()).end().build();
	}

}
