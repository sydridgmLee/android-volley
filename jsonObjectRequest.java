public void makeJsonObjectRequest() {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                TOP_EASY_NEWS_URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
