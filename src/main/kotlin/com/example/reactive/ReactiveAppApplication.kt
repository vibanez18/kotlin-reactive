package com.example.reactive

import com.example.reactive.domain.repository.CustomerRepository
import com.example.reactive.infrastucture.repository.db.Customer
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux


@SpringBootApplication
class ReactiveAppApplication

fun main(args: Array<String>) {
	runApplication<ReactiveAppApplication>(*args)
}

@Component
internal class Initializer(
	private val customerRepository: CustomerRepository,
	private val dbc: DatabaseClient
) : CommandLineRunner {

	@Throws(Exception::class)
	override fun run(vararg args: String) {
		val ddl = dbc
			.sql("create table customer(id serial primary key, name varchar(255) not null)")
			.fetch()
			.rowsUpdated()

		val names = Flux
			.just("Nilla", "José", "Jordi", "Francisco", "Pau", "Victor")
			.map { Customer(name = it) }
			.flatMap(customerRepository::save)


		val all = customerRepository.findAll()
		ddl.thenMany(names).thenMany(all).subscribe(System.out::println)
	}
}