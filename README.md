# green-library
spring boot project

INSERT INTO publishers (publisher_id, publisher_name) VALUES (1, 'Pearson');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (2, 'Penguin Random House');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (3, 'HarperCollins');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (4, 'Simon');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (5, 'Hachette Book Group');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (6, 'Macmillan Publishers');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (7, 'Scholastic');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (8, 'McGraw-Hill Education');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (9, 'Cengage Learning');
INSERT INTO publishers (publisher_id, publisher_name) VALUES (10, 'Springer Nature');

INSERT INTO genres (genre_id, genre_name) VALUES ('800', 'Fiction');
INSERT INTO genres (genre_id, genre_name) VALUES ('000', 'Non-fiction');
INSERT INTO genres (genre_id, genre_name) VALUES ('813', 'Mystery');
INSERT INTO genres (genre_id, genre_name) VALUES ('808', 'Science Fiction');
INSERT INTO genres (genre_id, genre_name) VALUES ('920', 'Biography');
INSERT INTO genres (genre_id, genre_name) VALUES ('813', 'Romance');
INSERT INTO genres (genre_id, genre_name) VALUES ('813', 'Thriller');
INSERT INTO genres (genre_id, genre_name) VALUES ('900', 'Historical');
INSERT INTO genres (genre_id, genre_name) VALUES ('813', 'Fantasy');
INSERT INTO genres (genre_id, genre_name) VALUES ('813', 'Horror');

INSERT INTO authors (author_id, author_name) VALUES (1, 'J.K. Rowling');
INSERT INTO authors (author_id, author_name) VALUES (2, 'George R.R. Martin');
INSERT INTO authors (author_id, author_name) VALUES (3, 'J.R.R. Tolkien');
INSERT INTO authors (author_id, author_name) VALUES (4, 'Agatha Christie');
INSERT INTO authors (author_id, author_name) VALUES (5, 'Stephen King');
INSERT INTO authors (author_id, author_name) VALUES (6, 'Isaac Asimov');
INSERT INTO authors (author_id, author_name) VALUES (7, 'Jane Austen');
INSERT INTO authors (author_id, author_name) VALUES (8, 'Mark Twain');
INSERT INTO authors (author_id, author_name) VALUES (9, 'Ernest Hemingway');
INSERT INTO authors (author_id, author_name) VALUES (10, 'F. Scott Fitzgerald');

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (1, 1, 1, '800', '823.91해', 'Harry Potter and the Sorcerer''s Stone', 'ex1.png', '978-0439708180', 'A1-01', '0', 'A young wizard''s journey begins.', TO_DATE('1997-06-26', 'YYYY-MM-DD'), 120);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (2, 2, 2, '800', '823.91마', 'A Game of Thrones', 'ex2.png', '978-0553103540', 'A1-02', '0', 'Noble families vie for control of the Iron Throne.', TO_DATE('1996-08-06', 'YYYY-MM-DD'), 150);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (3, 3, 3, '800', '823.91톨', 'The Fellowship of the Ring', 'ex3.png', '978-0261102354', 'A1-03', '1', 'A hobbit''s journey to destroy a powerful ring.', TO_DATE('1954-07-29', 'YYYY-MM-DD'), 200);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (4, 4, 4, '300', '363.25크', 'Murder on the Orient Express', 'ex4.png', '978-0062693662', 'A1-04', '0', 'A detective investigates a murder on a train.', TO_DATE('1934-01-01', 'YYYY-MM-DD'), 100);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (5, 5, 5, '400', '428.6킹', 'The Shining', 'ex5.png', '978-0385121675', 'A1-05', '1', 'A man goes mad in a haunted hotel.', TO_DATE('1977-01-28', 'YYYY-MM-DD'), 180);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (6, 6, 6, '500', '510.3아', 'Foundation', 'ex6.png', '978-0553293357', 'A1-06', '0', 'A visionary plans to save humanity through science.', TO_DATE('1951-05-01', 'YYYY-MM-DD'), 160);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (7, 7, 7, '800', '823.91제', 'Pride and Prejudice', 'ex7.png', '978-1503290563', 'A1-07', '0', 'A story of love and society in 19th century England.', TO_DATE('1813-01-28', 'YYYY-MM-DD'), 140);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (8, 8, 8, '800', '823.91마', 'The Adventures of Tom Sawyer', 'ex8.png', '978-0486400778', 'A1-08', '0', 'The adventures of a young boy growing up along the Mississippi River.', TO_DATE('1876-12-01', 'YYYY-MM-DD'), 130);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (9, 9, 9, '800', '813.54헤', 'The Old Man and the Sea', 'ex9.png', '978-0684830490', 'A1-09', '0', 'An old fisherman''s struggle with a giant marlin.', TO_DATE('1952-09-01', 'YYYY-MM-DD'), 110);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (10, 10, 10, '800', '813.54피', 'The Great Gatsby', 'ex10.png', '978-0743273565', 'A1-10', '1', 'A tragic story of Jay Gatsby and his unrequited love for Daisy Buchanan.', TO_DATE('1925-04-10', 'YYYY-MM-DD'), 170);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (11, 1, 1, '800', '823.91해', 'Harry Potter and the Chamber of Secrets', '000.png', '978-0439064873', 'A1-11', '0', 'Harry Potter returns to Hogwarts for his second year.', TO_DATE('1998-07-02', 'YYYY-MM-DD'), 110);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (12, 2, 2, '800', '823.91마', 'A Clash of Kings', '100.png', '978-0553579901', 'A1-12', '1', 'The battle for the Iron Throne intensifies.', TO_DATE('1998-11-16', 'YYYY-MM-DD'), 140);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (13, 3, 3, '800', '823.91톨', 'The Two Towers', '200.png', '978-0261102361', 'A1-13', '0', 'The second part of the epic journey of the Fellowship.', TO_DATE('1954-11-11', 'YYYY-MM-DD'), 200);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (14, 4, 4, '300', '363.25크', 'The ABC Murders', '300.png', '978-0062073563', 'A1-14', '0', 'Hercule Poirot investigates a series of alphabetically ordered murders.', TO_DATE('1936-01-06', 'YYYY-MM-DD'), 100);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (15, 5, 5, '400', '428.6킹', 'It', '400.png', '978-0450411434', 'A1-15', '1', 'A group of children face an evil entity that takes the form of a clown.', TO_DATE('1986-09-15', 'YYYY-MM-DD'), 180);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (16, 6, 6, '500', '510.3아', 'I, Robot', '500.png', '978-0553294385', 'A1-16', '0', 'A collection of stories about robots and their interactions with humans.', TO_DATE('1950-12-02', 'YYYY-MM-DD'), 160);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (17, 7, 7, '600', '641.5오', 'Sense and Sensibility', '600.png', '978-0141439662', 'A1-17', '0', 'The story of the Dashwood sisters and their pursuit of love and happiness.', TO_DATE('1811-10-30', 'YYYY-MM-DD'), 140);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (18, 8, 8, '800', '823.91마', 'Adventures of Huckleberry Finn', '700.png', '978-0486403496', 'A1-18', '0', 'Huck Finn and Jim, a runaway slave, travel down the Mississippi River.', TO_DATE('1884-12-10', 'YYYY-MM-DD'), 130);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (19, 9, 9, '800', '823.91헤', 'For Whom the Bell Tolls', '800.png', '978-0684803357', 'A1-19', '0', 'A story of love and war set during the Spanish Civil War.', TO_DATE('1940-10-21', 'YYYY-MM-DD'), 110);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (20, 10, 10, '800', '823.91피', 'Tender Is the Night', '900.png', '978-0684801544', 'A1-20', '1', 'The tragic romance of Dick and Nicole Diver.', TO_DATE('1934-04-12', 'YYYY-MM-DD'), 170);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (21, 1, 1, '300', '363.25해', 'Harry Potter and the Prisoner of Azkaban', 'account_box.png', '978-0439136365', 'A1-21', '0', 'Harry Potter''s third year at Hogwarts is filled with new dangers.', TO_DATE('1999-07-08', 'YYYY-MM-DD'), 110);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (22, 2, 2, '300', '363.25마', 'A Storm of Swords', 'account_circle.png', '978-0553106633', 'A1-22', '1', 'The battle for the Iron Throne reaches its climax.', TO_DATE('2000-08-08', 'YYYY-MM-DD'), 140);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (23, 3, 3, '400', '428.6톨', 'The Return of the King', 'badge.png', '978-0261102378', 'A1-23', '0', 'The final part of the epic journey of the Fellowship.', TO_DATE('1955-10-20', 'YYYY-MM-DD'), 200);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (24, 4, 4, '400', '428.6크', 'Death on the Nile', 'BasementB1.png', '978-0062073556', 'A1-24', '0', 'Hercule Poirot investigates a murder on a Nile cruise.', TO_DATE('1937-11-01', 'YYYY-MM-DD'), 100);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (25, 5, 5, '500', '510.3킹', 'Carrie', 'bookCategory.jpg', '978-0307743664', 'A1-25', '1', 'A high school girl discovers her telekinetic powers.', TO_DATE('1974-04-05', 'YYYY-MM-DD'), 180);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (26, 6, 6, '500', '510.3아', 'The Gods Themselves', 'bookExtension.png', '978-0553288100', 'A1-26', '0', 'A novel about the dangers and possibilities of interdimensional communication.', TO_DATE('1972-03-01', 'YYYY-MM-DD'), 160);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (27, 7, 7, '600', '641.5오', 'Emma', 'bookExtensionChg.png', '978-0141439587', 'A1-27', '0', 'A young woman''s misguided matchmaking leads to complications.', TO_DATE('1815-12-23', 'YYYY-MM-DD'), 140);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (28, 8, 8, '600', '641.5마', 'The Prince and the Pauper', 'bookExtensionGreen.png', '978-0486411101', 'A1-28', '0', 'A story of two boys who exchange lives, leading to adventures and insights.', TO_DATE('1881-11-19', 'YYYY-MM-DD'), 130);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (29, 9, 9, '700', '741.5헤', 'A Farewell to Arms', 'bookGreen.png', '978-0684801469', 'A1-29', '0', 'A story of love and loss during World War I.', TO_DATE('1929-09-27', 'YYYY-MM-DD'), 110);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (30, 10, 10, '700', '741.5피', 'This Side of Paradise', 'bookReturn.png', '978-0684801803', 'A1-30', '1', 'The story of Amory Blaine''s journey from adolescence to adulthood.', TO_DATE('1920-03-26', 'YYYY-MM-DD'), 170);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (31, 1, 1, '300', '363.25해', 'Harry Potter and the Goblet of Fire', 'bus.png', '978-0439139601', 'A2-01', '0', 'Harry Potter participates in a dangerous magical tournament.', TO_DATE('2000-07-08', 'YYYY-MM-DD'), 210);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (32, 2, 2, '300', '363.25마', 'A Feast for Crows', 'calendar_month.png', '978-0553801507', 'A2-02', '1', 'The aftermath of the war for the Iron Throne.', TO_DATE('2005-10-17', 'YYYY-MM-DD'), 220);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (33, 3, 3, '400', '428.6톨', 'The Silmarillion', 'calendar.png', '978-0618391110', 'A2-03', '0', 'The history of Middle-earth before The Lord of the Rings.', TO_DATE('1977-09-15', 'YYYY-MM-DD'), 230);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (34, 4, 4, '400', '428.6크', 'The Murder of Roger Ackroyd', 'calendarChg.png', '978-0062073564', 'A2-04', '0', 'Hercule Poirot solves a murder in a small village.', TO_DATE('1926-06-01', 'YYYY-MM-DD'), 240);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (35, 5, 5, '300', '363.25킹', 'Pet Sematary', 'gotoPage.png', '978-0450038341', 'A2-05', '1', 'A family discovers a graveyard that brings the dead back to life.', TO_DATE('1983-11-14', 'YYYY-MM-DD'), 250);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (36, 6, 6, '500', '510.3아', 'The End of Eternity', 'help.png', '978-0553286426', 'A2-06', '0', 'A technician who controls time faces a moral dilemma.', TO_DATE('1955-08-01', 'YYYY-MM-DD'), 260);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (37, 7, 7, '600', '641.5오', 'Northanger Abbey', 'history.png', '978-0141439792', 'A2-07', '0', 'A young woman''s adventures and romantic entanglements.', TO_DATE('1817-12-01', 'YYYY-MM-DD'), 270);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (38, 8, 8, '700', '741.5마', 'The Tragedy of Pudd''nhead Wilson', 'holdingPeople.png', '978-0486427041', 'A2-08', '0', 'A courtroom drama in a small town.', TO_DATE('1894-11-10', 'YYYY-MM-DD'), 280);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (39, 9, 9, '700', '741.5헤', 'The Sun Also Rises', 'helpGreen.png', '978-0684830511', 'A2-09', '0', 'A story of disillusioned expatriates in the 1920s.', TO_DATE('1926-10-22', 'YYYY-MM-DD'), 290);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (40, 10, 10, '700', '741.5피', 'The Beautiful and Damned', 'helpChg.png', '978-0684801541', 'A2-10', '1', 'The rise and fall of Anthony and Gloria Patch.', TO_DATE('1922-03-20', 'YYYY-MM-DD'), 300);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (41, 1, 1, '300', '363.25해', 'Harry Potter and the Order of the Phoenix', 'circleArrowGreenRight.png', '978-0439358064', 'A2-11', '0', 'Harry Potter battles the forces of evil.', TO_DATE('2003-06-21', 'YYYY-MM-DD'), 310);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (42, 2, 2, '300', '363.25마', 'A Dance with Dragons', 'floor4F.png', '978-0553801477', 'A2-12', '1', 'The fight for the Iron Throne continues.', TO_DATE('2011-07-12', 'YYYY-MM-DD'), 320);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (43, 3, 3, '400', '428.6톨', 'Unfinished Tales', 'go.png', '978-0618138098', 'A2-13', '0', 'Stories that expand the mythology of Middle-earth.', TO_DATE('1980-10-02', 'YYYY-MM-DD'), 330);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (44, 4, 4, '400', '428.6크', 'And Then There Were None', 'goback.png', '978-0062073489', 'A2-14', '0', 'Ten strangers are invited to an island where they are killed one by one.', TO_DATE('1939-11-06', 'YYYY-MM-DD'), 340);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (45, 5, 5, '300', '363.25킹', 'Misery', 'calendar-clock.png', '978-0450417394', 'A2-15', '1', 'An author is held captive by an obsessed fan.', TO_DATE('1987-06-08', 'YYYY-MM-DD'), 350);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (46, 6, 6, '500', '510.3아', 'The Stars, Like Dust', 'doubleArrowUp.png', '978-0553293424', 'A2-16', '0', 'A young man uncovers a conspiracy against his home planet.', TO_DATE('1951-01-01', 'YYYY-MM-DD'), 360);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (47, 7, 7, '600', '641.5오', 'Persuasion', 'circleArrowGreenLeft.png', '978-0141439686', 'A2-17', '0', 'A second chance at love for Anne Elliot and Captain Wentworth.', TO_DATE('1818-12-20', 'YYYY-MM-DD'), 370);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (48, 1, 1, '300', '363.25해', 'Harry Potter and the Sorcerer''s Stone', 'search.png', '978-0439708180', 'A1-01', '0', 'A young wizard''s journey begins.', TO_DATE('1997-06-26', 'YYYY-MM-DD'), 120);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (49, 2, 2, '300', '363.25마', 'A Game of Thrones', 'searchBtn.png', '978-0553593716', 'A1-02', '0', 'A story of power and betrayal in the Seven Kingdoms.', TO_DATE('1996-08-06', 'YYYY-MM-DD'), 150);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (50, 3, 3, '400', '428.6톨', 'The Hobbit', 'searchBtnGreen.png', '978-0618968633', 'A1-03', '0', 'Bilbo Baggins'' adventure to reclaim a lost kingdom.', TO_DATE('1937-09-21', 'YYYY-MM-DD'), 200);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (51, 4, 4, '400', '428.6크', 'Murder on the Orient Express', 'SmallThingsLikeThis.jpg', '978-0062693662', 'A1-04', '0', 'Detective Hercule Poirot investigates a murder.', TO_DATE('1934-01-01', 'YYYY-MM-DD'), 180);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (52, 5, 5, '300', '363.25킹', 'The Shining', 'subway.png', '978-0307743657', 'A1-05', '0', 'A family''s winter retreat turns into a nightmare.', TO_DATE('1977-01-28', 'YYYY-MM-DD'), 250);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (53, 6, 6, '500', '510.3아', 'Foundation', 'unacceptable.png', '978-0553293357', 'A1-06', '0', 'The epic tale of the Foundation and its struggle to save humanity.', TO_DATE('1951-06-01', 'YYYY-MM-DD'), 300);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (54, 7, 7, '600', '641.5오', 'Pride and Prejudice', 'userfind.png', '978-1503290563', 'A1-07', '0', 'A classic tale of love and social standing.', TO_DATE('1813-01-28', 'YYYY-MM-DD'), 400);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (55, 8, 8, '700', '741.5마', 'Adventures of Huckleberry Finn', 'useTime.png', '978-0486280615', 'A1-08', '0', 'The adventures of a young boy along the Mississippi River.', TO_DATE('1884-12-10', 'YYYY-MM-DD'), 350);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (56, 9, 9, '700', '741.5헤', 'The Old Man and the Sea', 'useTimeChg.png', '978-0684801223', 'A1-09', '0', 'The story of an epic struggle between an old fisherman and a giant marlin.', TO_DATE('1952-09-01', 'YYYY-MM-DD'), 320);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (57, 10, 10, '700', '741.5피', 'The Great Gatsby', 'useTimeGreen.png', '978-0743273565', 'A1-10', '0', 'The story of the mysteriously wealthy Jay Gatsby and his love for Daisy Buchanan.', TO_DATE('1925-04-10', 'YYYY-MM-DD'), 270);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (58, 1, 1, '300', '363.25해', 'Harry Potter and the Chamber of Secrets', 'vision.png', '978-0439064873', 'A2-01', '0', 'Harry returns for his second year at Hogwarts.', TO_DATE('1998-07-02', 'YYYY-MM-DD'), 220);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (59, 2, 2, '300', '363.25마', 'A Clash of Kings', 'wait.png', '978-0553579901', 'A2-02', '0', 'The second book in the A Song of Ice and Fire series.', TO_DATE('1999-02-02', 'YYYY-MM-DD'), 170);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (60, 3, 3, '400', '428.6톨', 'The Lord of the Rings: The Fellowship of the Ring', 'whiteeye.png', '978-0618574940', 'A2-03', '0', 'The first part of Tolkien''s epic masterpiece.', TO_DATE('1954-07-29', 'YYYY-MM-DD'), 210);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (61, 4, 4, '400', '428.6크', 'The ABC Murders', 'wood.png', '978-0062073587', 'A2-04', '0', 'Poirot investigates a series of alphabetical murders.', TO_DATE('1936-01-06', 'YYYY-MM-DD'), 160);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (62, 5, 5, '300', '363.25킹', 'It', 'woodLeaf.jpg', '978-1501142970', 'A2-05', '0', 'A group of children face an evil entity in their small town.', TO_DATE('1986-09-15', 'YYYY-MM-DD'), 230);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (63, 6, 6, '500', '510.3아', 'I, Robot', 'x-icon.png', '978-0553382563', 'A2-06', '0', 'A collection of stories about robots and their interactions with humans.', TO_DATE('1950-12-02', 'YYYY-MM-DD'), 190);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (64, 7, 7, '600', '641.5오', 'Sense and Sensibility', '녹나무의여신.jpg', '978-1503290310', 'A2-07', '0', 'The story of the Dashwood sisters and their romantic entanglements.', TO_DATE('1811-01-01', 'YYYY-MM-DD'), 240);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (65, 8, 8, '700', '741.5마', 'The Adventures of Tom Sawyer', '도서관 로고 test.png', '978-0486400778', 'A2-08', '0', 'Tom Sawyer''s adventures along the Mississippi River.', TO_DATE('1876-06-01', 'YYYY-MM-DD'), 260);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (66, 9, 9, '700', '741.5헤', 'For Whom the Bell Tolls', '도서관-로고크게.png', '978-0684803357', 'A2-09', '0', 'The story of an American dynamiter during the Spanish Civil War.', TO_DATE('1940-10-21', 'YYYY-MM-DD'), 290);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (67, 10, 10, '700', '741.5피', 'Tender Is the Night', '로고.png', '978-0684801544', 'A2-10', '0', 'A tragic romance set in the French Riviera.', TO_DATE('1934-04-12', 'YYYY-MM-DD'), 200);

INSERT INTO books (book_id, author_id, publisher_id, genre_id, genre_fullname, title, img, isbn, location, availability, summary, publication_date, borrow_count)
VALUES (68, 1, 1, '300', '363.25해', 'Harry Potter and the Prisoner of Azkaban', '비전.png', '978-0439136365', 'A3-01', '0', 'Harry faces new dangers in his third year at Hogwarts.', TO_DATE('1999-07-08', 'YYYY-MM-DD'), 230);
