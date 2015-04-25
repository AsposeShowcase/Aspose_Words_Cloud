package com.aspose.cloud.sdk.cells.model;

import com.aspose.cloud.sdk.common.BaseResponse;
import com.aspose.cloud.sdk.common.LinkModel;
import com.google.gson.annotations.SerializedName;

public class RowResponse extends BaseResponse
{
    @SerializedName("Row")
    public RowData row;

    public class RowData {
        public LinkModel link;
        public int Index;
        public int GroupLevel;
        public int Height;
        public boolean IsHidden;
        public boolean IsBlank;
        public boolean IsHeightMatched;
    }

}