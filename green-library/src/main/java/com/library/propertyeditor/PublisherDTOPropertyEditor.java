package com.library.propertyeditor;

import com.library.dto.admin._normal.PublisherDTO;

import java.beans.PropertyEditorSupport;

public class PublisherDTOPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setPublisherName(text.trim());
        setValue(publisherDTO);
    }
}

