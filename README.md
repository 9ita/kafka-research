# Kafka

| 설정 항목                                             | 설정값 (예시)                       | 설명                                                                                   |
|-------------------------------------------------------|-------------------------------------|----------------------------------------------------------------------------------------|
| **기본 설정**                                         |                                     |                                                                                        |
| `bootstrap.servers`                                   | `192.168.1.1:9092,192.168.1.2:9092` | Kafka 브로커 리스트. 초기 연결 시 필요한 브로커들의 IP와 포트 정보.                    |
| `acks`                                                | `all`                               | 전송된 메시지에 대한 ack의 수준. (`0`, `1`, `all` 선택 가능)                            |
| `retries`                                             | `3`                                 | 메시지 전송 실패 시 재시도 횟수.                                                       |
| `retry.backoff.ms`                                    | `100`                               | 재시도 간격 (밀리초 단위).                                                             |
| `buffer.memory`                                       | `33554432`                          | 프로듀서가 버퍼링할 수 있는 메모리 크기 (바이트 단위).                                  |
| `compression.type`                                    | `gzip`                              | 메시지 압축 방식 (`none`, `gzip`, `snappy`, `lz4`, `zstd`).                             |
| `linger.ms`                                           | `5`                                 | 프로듀서가 배치로 묶기 위해 전송을 지연할 시간 (밀리초).                                |
| `batch.size`                                          | `16384`                             | 한 배치에 포함될 최대 메시지 크기 (바이트 단위).                                        |
| `max.in.flight.requests.per.connection`               | `5`                                 | 연결당 비동기 요청의 최대 수.                                                          |
| `enable.idempotence`                                  | `true`                              | 멱등성을 보장하여 중복 메시지를 방지.                                                  |
| `transactional.id`                                    | `my-transaction-id`                 | 트랜잭션을 사용하는 프로듀서의 ID. 트랜잭션 기반 전송 시 필요.                          |
| **컨슈머 설정**                                       |                                     |                                                                                        |
| `session.timeout.ms`                                  | `10000`                             | 컨슈머가 브로커와 연결을 유지하는 최대 대기 시간 (밀리초).                             |
| `fetch.min.bytes`                                     | `1`                                 | 컨슈머가 최소한으로 가져올 데이터 크기 (바이트 단위).                                   |
| `max.poll.records`                                    | `500`                               | 컨슈머가 한 번의 poll 요청으로 가져올 최대 레코드 수.                                   |
| `auto.offset.reset`                                   | `earliest`                          | 새로운 컨슈머가 파티션에 할당될 때 가져올 오프셋의 위치 (`earliest`, `latest`, `none`). |
| `group.id`                                            | `consumer-group-1`                  | 컨슈머 그룹 ID. 같은 그룹의 컨슈머들은 파티션을 나눠 처리함.                             |
| **프로듀서 설정**                                     |                                     |                                                                                        |
| `client.id`                                           | `my-producer-id`                    | 클라이언트 식별자. 클라이언트 애플리케이션의 식별을 위해 사용됨.                         |
| `max.request.size`                                    | `1048576`                           | 브로커로 전송할 수 있는 최대 요청 크기 (바이트 단위).                                   |
| `delivery.timeout.ms`                                 | `120000`                            | 메시지 전송 후 ack를 기다리는 최대 시간 (밀리초).                                       |
| `request.timeout.ms`                                  | `30000`                             | 프로듀서가 브로커로 전송된 요청의 응답을 기다리는 최대 시간 (밀리초).                    |
| **기타 설정**                                         |                                     |                                                                                        |
| `metrics.recording.level`                             | `INFO`                              | 메트릭 수집 수준 (`INFO`, `DEBUG`).                                                     |
| `connections.max.idle.ms`                             | `540000`                            | 유휴 연결이 끊어지기 전에 유지될 최대 시간 (밀리초).                                    |
| `receive.buffer.bytes`                                | `32768`                             | 소켓의 수신 버퍼 크기 (바이트 단위).                                                    |
| `send.buffer.bytes`                                   | `131072`                            | 소켓의 송신 버퍼 크기 (바이트 단위).                                                    |


## Container Orchestration 특수

| 설정 항목                                             | 설정값 (예시)                       | 설명                                                                                   |
|-------------------------------------------------------|-------------------------------------|----------------------------------------------------------------------------------------|
| **기본 네트워크 설정**                                |                                     |                                                                                        |
| `advertised.listeners`                                | `PLAINTEXT://kafka-0.kafka:9092`    | Kafka 브로커가 클러스터 외부에서 접근할 수 있도록 광고할 리스너 주소 (DNS 또는 IP).       |
| `listeners`                                           | `PLAINTEXT://0.0.0.0:9092`          | Kafka 브로커가 수신 대기할 리스너 주소. 모든 인터페이스에서 수신 대기하려면 `0.0.0.0`.    |
| `inter.broker.listener.name`                          | `PLAINTEXT`                         | 브로커 간 통신에 사용할 리스너 이름. 일반적으로 `PLAINTEXT` 또는 `SSL`.                  |
| `listener.security.protocol.map`                      | `PLAINTEXT:PLAINTEXT`               | 리스너에 대한 프로토콜을 정의. (예: `PLAINTEXT`, `SSL`)                                  |
| `host.name`                                           | `kafka-0.kafka.svc.cluster.local`   | 컨테이너 환경에서 사용하는 Kafka 브로커의 호스트 이름 (Kubernetes 서비스 이름).            |
| `log.dirs`                                            | `/kafka/kafka-logs`                 | 로그 데이터가 저장될 경로. 컨테이너 환경에서는 볼륨을 사용해 외부 저장소와 연결 필요.     |
| **Kubernetes 관련 설정 (특히 StatefulSets)**          |                                     |                                                                                        |
| `pod.affinity`                                        | `preferredDuringSchedulingIgnoredDuringExecution` | Kafka 파드가 특정 노드에 배치되도록 우선순위를 설정하는 affinity 규칙.                 |
| `statefulset.volumeClaimTemplates`                    | `persistentVolumeClaim`             | Kafka 로그 데이터를 위한 영구 스토리지 볼륨을 정의.                                    |
| `kafka.broker.rack`                                   | `zone-a`                            | Kafka 브로커가 위치한 물리적 위치를 지정하여, 다중 데이터 센터 또는 AZ 환경에서 사용.    |
| **보안 설정 (컨테이너 환경)**                         |                                     |                                                                                        |
| `security.protocol`                                   | `SSL`                              | Kafka와 클라이언트 또는 브로커 간 통신을 보호하기 위한 보안 프로토콜.                    |
| `ssl.keystore.location`                               | `/var/private/ssl/kafka.server.keystore.jks` | SSL 키스토어 파일 경로 (컨테이너 내에 위치).                                             |
| `ssl.keystore.password`                               | `your-keystore-password`            | SSL 키스토어의 비밀번호.                                                                |
| `ssl.truststore.location`                             | `/var/private/ssl/kafka.server.truststore.jks` | SSL 트러스트스토어 파일 경로 (컨테이너 내에 위치).                                        |
| `ssl.truststore.password`                             | `your-truststore-password`          | SSL 트러스트스토어의 비밀번호.                                                          |
| **리소스 관리 (컨테이너 환경)**                        |                                     |                                                                                        |
| `KAFKA_HEAP_OPTS`                                     | `-Xmx1G -Xms1G`                     | Kafka JVM의 힙 메모리 설정. 컨테이너 환경에서는 리소스 제약이 있을 수 있으므로 적절히 설정.|
| `KAFKA_OPTS`                                          | `-XX:+UseG1GC -XX:MaxGCPauseMillis=20` | JVM 가비지 컬렉션 튜닝. 컨테이너 환경에서는 자원 제약이 있으므로 GC 튜닝이 중요함.       |
| **컨테이너 관련 설정 (Docker/Kubernetes)**            |                                     |                                                                                        |
| `KAFKA_ADVERTISED_HOST_NAME`                          | `kafka-0.kafka.svc.cluster.local`   | Kafka 컨테이너의 호스트 이름을 명시적으로 설정하여 외부 서비스들이 올바르게 연결되도록 함. |
| `KAFKA_ADVERTISED_PORT`                               | `9092`                              | 외부에서 접근할 Kafka 브로커의 포트.                                                    |
| `KAFKA_ZOOKEEPER_CONNECT`                             | `zookeeper-0.zookeeper:2181`        | Zookeeper와 Kafka 브로커 간의 연결 정보. 컨테이너 환경에서는 DNS를 사용하여 연결.        |
| **메트릭 및 모니터링**                                |                                     |                                                                                        |
| `JMX_PORT`                                            | `9999`                              | 컨테이너 내부에서 JMX 포트를 노출하여 모니터링 툴과 연동.                               |
| `KAFKA_JMX_OPTS`                                      | `-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false` | JMX 설정 옵션으로, Kafka의 성능 모니터링을 위한 포트 및 인증 설정.                    |

