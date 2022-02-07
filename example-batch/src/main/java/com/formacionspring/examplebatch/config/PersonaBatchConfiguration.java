package com.formacionspring.examplebatch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.formacionspring.examplebatch.model.Persona;

@Configuration
@EnableBatchProcessing
public class PersonaBatchConfiguration {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public FlatFileItemReader<Persona> readFromCsv(){
		FlatFileItemReader<Persona> reader= new FlatFileItemReader<Persona>();
		reader.setResource(new ClassPathResource("persona.csv"));
		reader.setLineMapper(new DefaultLineMapper<Persona>() {
			{
			setLineTokenizer(new DelimitedLineTokenizer(){
				{
				setNames(Persona.fields());
				}
			});
		
			setFieldSetMapper(new BeanWrapperFieldSetMapper<Persona>() {
				{
					setTargetType(Persona.class);
				}
			});
		}
		});
		
		return reader;
	}
	
	@Bean
	public JdbcBatchItemWriter<Persona> writerIntoDB(){
		
		JdbcBatchItemWriter<Persona> writer = new JdbcBatchItemWriter<Persona>();
		writer.setDataSource(dataSource);
		writer.setSql("insert into personas(id,nombre,apellido,email) values(:id,:nombre,:apellido,:email);");
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Persona>());
		return writer;
		
	}
	
	@Bean
	public Step step() {
		return stepBuilderFactory.get("step").<Persona,Persona>chunk(10)
				.reader(readFromCsv()).writer(writerIntoDB()).build();
	}
	
	@Bean
	public Job job() {
		return jobBuilderFactory.get("job").flow(step()).end().build();
	}
	
	

}
