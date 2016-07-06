public void makeStringRequest() {
        StringRequest stringRequest =
                new StringRequest(
                        Request.Method.GET,
                        TOP_EASY_NEWS_URL,
                        new Response.Listener<String>(){
                            @Override
                            public void onResponse(String response) {
                                // Log.d("*********", response);
                                // Log.d("********&", response.indexOf("[")+"");
                                response = response.substring(3);
                                InputStream stream = new ByteArrayInputStream(response.getBytes(StandardCharsets.UTF_8));
                                List<News_Item> news_items = new ArrayList<News_Item>();
                                try {
                                    news_items = readJSONStream(stream);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                setupRecyclerView(rv, news_items);
                            }
                        },
                        new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        }
                );
        requestQueue.add(stringRequest);
    }
    
    public List<News_Item> readJSONStream(InputStream inputStream) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            return readNewsArray(reader);
        }
        finally {
            reader.close();
        }
    }

    public List<News_Item> readNewsArray(JsonReader reader) throws IOException {
        List<News_Item> items = new ArrayList<News_Item>();

        reader.beginArray();
        while(reader.hasNext()) {
            items.add(readNews(reader));
        }
        reader.endArray();
        return items;
    }

    public News_Item readNews(JsonReader reader) throws  IOException {
        int top_priority_number = -1;
        boolean top_display_flag = false;
        String news_id = null;
        String news_prearranged_time = null;
        String title = null;
        boolean has_news_web_image = false;
        boolean has_news_web_movie = false;
        boolean has_news_easy_voice = false;
        String news_web_image_uri = null;
        String news_web_movie_uri = null;
        String news_easy_voice_uri = null;

        reader.beginObject();
        while(reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("top_priority_number")) {
                top_priority_number = reader.nextInt();
            }
            else if (name.equals("top_display_flag")) {
                top_display_flag = reader.nextBoolean();
            }
            else if (name.equals("news_id")) {
                news_id = reader.nextString();
            }
            else if (name.equals("news_prearranged_time")) {
                news_prearranged_time = reader.nextString();
            }
            else if (name.equals("title")) {
                title = reader.nextString();
            }
            else if (name.equals("has_news_web_image")) {
                has_news_web_image = reader.nextBoolean();
            }
            else if (name.equals("has_news_web_movie")) {
                has_news_web_movie = reader.nextBoolean();
            }
            else if (name.equals("has_news_easy_voice")) {
                has_news_easy_voice = reader.nextBoolean();
            }
            else if (name.equals("news_web_image_uri")) {
                news_web_image_uri = reader.nextString();
            }
            else if (name.equals("news_web_movie_uri")) {
                news_web_movie_uri = reader.nextString();
            }
            else if (name.equals("news_easy_voice_uri")) {
                news_easy_voice_uri = reader.nextString();
            }else {
                reader.skipValue();
            }
        }
        reader.endObject();
        Log.d("&&&&", news_web_image_uri);
        return new News_Item(
                top_priority_number,
                top_display_flag,
                news_id,
                news_prearranged_time,
                title,
                has_news_web_image,
                has_news_web_movie,
                has_news_easy_voice,
                news_web_image_uri,
                news_web_movie_uri,
                news_easy_voice_uri
        );
    }
