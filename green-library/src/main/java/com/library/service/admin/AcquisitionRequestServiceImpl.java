package com.library.service.admin;

import com.library.dto.admin._normal.WishlistDTO;
import com.library.repository.admin.AcquisitionRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AcquisitionRequestServiceImpl implements AcquisitionRequestService {

    @Autowired
    private AcquisitionRequestRepository acquisitionRequestRepository;

    //    전체 확인
    @Override
    public List<WishlistDTO> allAcquisitionManage() {
        return acquisitionRequestRepository.allAcquisitionManage();
    }

    @Override
    public List<WishlistDTO> findAcquisitionByTotal(String total) {
        return acquisitionRequestRepository.findAcquisitionByTotal(total);
    }

    //    제목으로 확인
    @Override
    public List<WishlistDTO> findAcquisitionByTitle(String title) {
        return acquisitionRequestRepository.findAcquisitionByTitle(title);
    }

    //     저자로 확인
    @Override
    public List<WishlistDTO> findAcquisitionByAuthor(String author) {
        return acquisitionRequestRepository.findAcquisitionByAuthor(author);
    }

    //     십진분류로 확인
    @Override
    public List<WishlistDTO> findAcquisitionByPublish(String publish) {
        return acquisitionRequestRepository.findAcquisitionByPublish(publish);
    }

    //    구매승인
    @Override
    public WishlistDTO acceptsAcquisition(List<String> requestId) {
        acquisitionRequestRepository.acceptsAcquisition(requestId);
        return null;
    }

    //    구매거절
    @Override
    public WishlistDTO deleteAcquisition(List<String> requestId) {
        acquisitionRequestRepository.deleteAcquisition(requestId);
        return null;
    }

    @Override
    public List<WishlistDTO> filterByCompleteKind(List<WishlistDTO> wishBooks, String completeKind) {
        List<WishlistDTO> filteredList = new ArrayList<>();
        for (WishlistDTO book : wishBooks) {
            switch (completeKind.toLowerCase()) {
                case "wait":
                    if (book.getComplete()=='W') {
                        filteredList.add(book);
                    }
                    break;
                case "accept":
                    if (book.getComplete()=='Y') {
                        filteredList.add(book);
                    }
                    break;
                case "refuse":
                    if (book.getComplete()=='N') {
                        filteredList.add(book);
                    }
                    break;
            }
        }
        return filteredList;
    }

    @Override
    public WishlistDTO getWishById(int wishlistId) {
        return acquisitionRequestRepository.getWishById(wishlistId);
    }

    @Override
    public void updateWishBook(int wishlistId, String isbn, String title, String author, String publisher, Date publicationDate, String content) {
        WishlistDTO wishBook = acquisitionRequestRepository.getWishById(wishlistId);
        if (wishBook != null) {
            wishBook.setWishIsbn(isbn);
            wishBook.setWishTitle(title);
            wishBook.setWishAuthor(author);
            wishBook.setWishPublisher(publisher);
            wishBook.setWishPublication(publicationDate);
            wishBook.setContents(content);
            acquisitionRequestRepository.updateBook(wishBook);
        } else {
            throw new RuntimeException("위시리스트를 찾을 수 없습니다: " + wishlistId);
        }

    }
}