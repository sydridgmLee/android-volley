public void makeStringRequest() {
        StringRequest stringRequest =
                new StringRequest(
                        Request.Method.GET,
                        TOP_EASY_NEWS_URL,
                        new Response.Listener<String>(){
                            @Override
                            public void onResponse(String response) {
                                Log.d("*********", response);
                                Log.d("********&", response.indexOf("[")+"");
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
