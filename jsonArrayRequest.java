public void makeJsonArrayRequest() {
        final List<News_Item> news_items = new ArrayList<News_Item>();
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                TOP_EASY_NEWS_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArrayResponse) {
                        for (int i = 0; i < jsonArrayResponse.length(); i++) {
                            try {
                                JSONObject jsonObject = (JSONObject) jsonArrayResponse.get(i);

                                int top_priority_number = jsonObject.getInt("top_priority_number");;
                                boolean top_display_flag = jsonObject.getBoolean("top_display_flag");
                                String news_id = jsonObject.getString("news_id");
                                String news_prearranged_time = jsonObject.getString("news_prearranged_time");
                                String title = jsonObject.getString("title");
                                boolean has_news_web_image = jsonObject.getBoolean("has_news_web_image");
                                boolean has_news_web_movie = jsonObject.getBoolean("has_news_web_movie");
                                boolean has_news_easy_voice = jsonObject.getBoolean("has_news_easy_voice");
                                String news_web_image_uri = jsonObject.getString("news_web_image_uri");
                                String news_web_movie_uri = jsonObject.getString("news_web_movie_uri");
                                String news_easy_voice_uri = jsonObject.getString("news_easy_voice_uri");

                                news_items.add(new News_Item(
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
                                        news_easy_voice_uri));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        RequestQueue.add(jsonArrayRequest);
    }
