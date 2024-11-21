package com.ng.NgBank.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceipientDetails {
    protected String receipentmail;
    protected String subject;
    protected String body;
    protected String attachment;
}

