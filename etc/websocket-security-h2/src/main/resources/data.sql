insert into user
values (1, 'admin-client', '$2a$10$IYB6yC7ANnc4N66SjttBcetmYsPdwOqbpuixIIyrAMDBehIkXeHem', 1, 'admin');

INSERT INTO user
VALUES (2, 'member-client', '$2a$10$IWdOGhzvnx5yPqQeyY5hQObDbWV6q94jdlDztNOSl5ygFQMrNUWmi', 1, 'member');

INSERT INTO user
VALUES (3, 'member-2-client', '$2a$10$vUX9hFCVVBrFHFWQoRJLUuJbSaZfdlT3.Gw3GAMpLLVTkRqXNlj.6', 1, 'member-2');

INSERT INTO user
VALUES (4, 'inactive-client', '$2a$10$5ZF0MFdZi2bLnO1OSAZ7LOib.maP1YG.RFYiBwrTNyJwQVKbIkl4S', 0, 'inactive');

INSERT INTO user
VALUES (5, 'non-member-client', '$2a$10$IYB6yC7ANnc4N66SjttBcetmYsPdwOqbpuixIIyrAMDBehIkXeHem', 1, 'non-member');

INSERT INTO channel
VALUES (1, 'channel-1', '1', '2,3,4');

INSERT INTO channel
VALUES (2, 'channel-2', '1', null);