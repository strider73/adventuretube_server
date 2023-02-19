//https://zetcode.com/springboot/controlleradvice/
package com.adventuretube.service.error;

public class YoutubeIdAlreadyExistException extends RuntimeException {
   public String contentId  ;
   public String contentTitle;
    public YoutubeIdAlreadyExistException(String contentId,String contentTile){
        super(String.format("Youtube contentId %s  and title %s is already exist", contentId,contentTile));
        this.contentId = contentId;
        this.contentTitle = contentTile;

    }
}
