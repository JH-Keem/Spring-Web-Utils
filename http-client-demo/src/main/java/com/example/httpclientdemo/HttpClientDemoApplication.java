package com.example.httpclientdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HttpClientDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpClientDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return args -> {
			System.out.println("데이터 받아오는중...");
			String joke = getJoke();
			System.out.println("Joke: " + joke);
		};
	}
	
	//	"https://official-joke-api.appspot.com/random_joke" 주소로 요청 보내고, 응답 받아 출력하기	
	private String getJoke() throws Exception {
		//	URL 객체 생성을 위해 URI객체의 static 메서드인 create의 매개변수로 문자열을 넣고 toUrl메서드로 생성
		URI uri = URI.create("https://official-joke-api.appspot.com/random_joke");
		URL url = uri.toURL();
		
		//	생성한 URL에 대한 연결을 나타내는 URLConnection객체를 반환하는 openConnection메서드를 사용,
		//	HttpURLConnection 으로 형변환 (Http 프로토콜을 사용하는 URL에 대해 가능한 작업으로, HTTP에 특화된 추가 메서드와 필드를 제공한다.)
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		//	HttpURLConnection의 HTTP 메서드는 "GET"을 사용하겠음을 설정
		con.setRequestMethod("GET");
		
		//	HttpURLConnection 객체에서 입력 스트림을 얻는다.
		//	(이 스트림은 서버로부터 받은 raw 바이트 데이터를 제공함.)
		//	-> 해당 InputStream을 문자기반 Reader(InputStreamReader)로 변환한다.
		//	(바이트 스트림을 문자 스트림으로 변환하는 역할을 하며, 대개 UTF-8을 사용해 바이트를 문자로 디코딩한다.)
		//	-> 이를 버퍼링된 reader로 래핑한다(BufferedReader)
		//	(버퍼링을 통해 읽기 성능을 향상시키고, 한 번에 한 줄씩 읽는 등의 편리한 메서드를 제공한다.)
		
		//	바이트 데이터를 직접 출력해보고 싶다면 ? => printByteData() 메서드 호출		 
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
		String inputLine;
		StringBuilder content = new StringBuilder();
		while((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		con.disconnect();
		
		return content.toString();
	}
	
	public void printByteData(HttpURLConnection con) throws IOException {
		System.out.println("Raw byte data: ");
		InputStream inputStream = con.getInputStream();
		
		//	1KB크기의 배열 생성, 각 요소당 1Byte 저장
		byte[] buffer = new byte[1024];
		int bytesRead;
		StringBuilder byteString = new StringBuilder();
		
		//	스트림에서 바이트를 읽어 buffer에 저장하고, 실제로 읽은 바이트 수(int)를 반환, bytesRead에 저장
		while((bytesRead = inputStream.read(buffer)) != -1) {
			for(int i = 0; i < bytesRead; i++) {
				//	읽은 바이트 수 만큼 반복해, 각 바이트를 16진수 문자열로 변환 (%02X => 한자리의경우 앞에 0을 채운 16진수 포멧)
				byteString.append(String.format("%02X ",  buffer[i]));
			}
		}
		System.out.println(byteString.toString());
	}
	
}
