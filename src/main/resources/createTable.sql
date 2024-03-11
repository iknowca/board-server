CREATE TABLE IF NOT EXISTS board_content
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    content TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS board
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    title      VARCHAR(255)                                                    NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP                             NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NULL,
    writer_id  BIGINT                                                          NULL,
    content_id BIGINT                                                          NOT NULL,
    FOREIGN KEY (content_id) REFERENCES board_content (id) ON DELETE CASCADE
);

