#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>

#define BUF_SIZE 1024
void error_handling(char *message);

int main(int argc, char *argv[]) {

	//소켓 파일 디스크립터 변수 선언
	int serv_sock, clnt_sock;	//서버소켓-연결 요청을 받는 소켓, 클라이언트소켓-data 송수신을 위한 소켓
	char message[BUF_SIZE];	//전송 할 데이더가 담긴 char형 배열
	int str_len, i;	//str_len: raed 함수에서 몇 글자를 읽어왔는지 저장하는 변수, i: 반복문에서 쓰일 변수

	struct sockaddr_in serv_adr;	//서버소켓에 주소정보 할당 할 때 쓰이는 구조체
	struct sockaddr_in clnt_adr;	//accept함수 인자로 들어갈 구조체 - 연결 요청을 한 클라이언트의 주소정보를 함께 저장
	socklen_t clnt_adr_sz;	//clnt_adr의 크기를 담아주는 변수

	if (argc != 2) {	//실행 방법에 대한 오류처리 방법 안내
		printf("Usage: %s <port>\n", argv[0]);
		exit(1);
	}

	/*중요한 부분!*/
	serv_sock = socket(PF_INET, SOCK_STREAM, 0);	//소켓함수를 통해서 연결지향형 소켓 tcp소켓 생성, 파일 디스크립터가 저장
	if (serv_sock == -1)
		error_handling("socket() error");

	//주소 정보를 담는 구조체 초기화
	memset(&serv_adr, 0, sizeof(serv_adr));	
	serv_adr.sin_family = AF_INET;
	serv_adr.sin_addr.s_addr = htonl(INADDR_ANY);	//자신의 ip를 자동으로 넣어준다
	serv_adr.sin_port = htons(atoi(argv[1]));	//port 번호에 프로그래머가 입력한 문자열 값을 다시 int형으로 바꾸고 network byte 순서로 바꿔서 저장
	
	if (bind(serv_sock, (struct sockaddr*)&serv_adr, sizeof(serv_adr)) == -1)	//주소 직접 할당
		error_handling("bind() error");
		
	if (listen(serv_sock, 5) == -1)	//서버 소켓이 인자로 들어가며, 연결 요청 대기 큐의 크기는 5
		error_handling("listen() error");

	clnt_adr_sz = sizeof(clnt_adr); //client address의 크기를 이 변수에 저장

	for (i = 0; i < 5; i++) {
		clnt_sock = accept(serv_sock, (struct sockaddr*)&clnt_adr, &clnt_adr_sz);	//실제로 데이터 송수신을 담당하는 소켓
		if (clnt_sock == -1)
			error_handling("accept() error");
		else
			printf("Connected client %d \n", i + 1);

		while ((str_len = read(clnt_sock, message, BUF_SIZE)) != 0) //clnt_sock에 있는 data를 읽어와서 메시지 배열에 저장
			write(clnt_sock, message, str_len);	//뭔가 들어왔다면 읽은 만큼 다시 보내준다!

		close(clnt_sock);	//클라이언트 소켓만 닫는다
	}

	close(serv_sock); //모든 처리가 끝나면 반복문 빠져나와서 서버소켓도 닫는다
	return 0;
}

void error_handling(char *message) {
	fputs(message, stderr);
	fputc('\n', stderr);
	exit(1);
}