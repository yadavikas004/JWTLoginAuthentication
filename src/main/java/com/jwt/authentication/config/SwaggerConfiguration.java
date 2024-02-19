package com.jwt.authentication.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Courses Learning",
                description = "Performing CRUD Operations",
                summary = "This Course API will add,delete,Create and Update",
                termsOfService = "Terms & Conditions",
                contact = @Contact(
                        name = "Vikas Yadav",
                        email = "yadavikas088@gmail.com"
                ),
                license = @License(
                        name = "Your Licence Number"
                ),
                version = "v1.0"
        ),
        servers = {
                @Server(
                        description = "Dev",
                        url = "http://localhost:8081"
                ),
                @Server(
                        description = "Test",
                        url = "http://localhost:8081"
                )
        }
)
public class SwaggerConfiguration {

}
