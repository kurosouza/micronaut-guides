package example.micronaut

import io.micronaut.http.HttpRequest
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Specification
import static io.micronaut.http.HttpStatus.UNAUTHORIZED
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import jakarta.inject.Inject

@MicronautTest
class BooksControllerSecuredSpec extends Specification {
    @Inject
    @Client('/')
    HttpClient httpClient

    void '/books is secured'() {
        when:
        httpClient.toBlocking().exchange(HttpRequest.GET('/books'))
        
        then:
        HttpClientResponseException e = thrown()
        UNAUTHORIZED == e.status
    }
}
