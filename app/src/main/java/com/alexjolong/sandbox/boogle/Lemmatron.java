package com.alexjolong.sandbox.boogle;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* Example response
{
  "metadata": {},
  "results": [
    {
      "id": "string",
      "language": "string",
      "lexicalEntries": [
        {
          "grammaticalFeatures": [
            {
              "text": "string",
              "type": "string"
            }
          ],
          "inflectionOf": [
            {
              "id": "string",
              "text": "string"
            }
          ],
          "language": "string",
          "lexicalCategory": "string",
          "text": "string"
        }
      ],
      "type": "string",
      "word": "string"
    }
  ]
}
*/

public class Lemmatron {

    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}