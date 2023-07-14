public class uasNomor3 {

    public static void main(String[] args) throws IOException {

//        REQUEST
        ConnectURI koneksiSaya = new ConnectURI();
        URL myAddress = koneksiSaya.buildURL("https://dummyjson.com/products/search?q=Laptop");

//        RESPONSE

        String response = koneksiSaya.getResponseFromHttpUrl(myAddress);
        System.out.println(response);

//        decoding JSON

        assert response != null;

        JSONArray responsedJSON = new JSONArray(response);

        JSONObject json = new JSONObject(responsedJSON);


        JSONArray productsArray = json.getJSONArray("products");
        ArrayList<Laptop> modelLaptop = new ArrayList<>();

        for (int i = 0; i<productsArray.length();i++){

            JSONObject productObject = productsArray.getJSONObject(i);
            Laptop laptop = new Laptop();

            laptop.setId(productObject.getString("id"));
            laptop.setTitle(productObject.getString("title"));
            laptop.setDescription(productObject.getString("description"));
            laptop.setPrice(productObject.getString("price"));
            laptop.setRating(productObject.getString("rating"));
            laptop.setBrand(productObject.getString("brand"));


            modelLaptop.add(laptop);
        }
        Laptop[] productArray = modelLaptop.toArray(new Laptop[0]);

        for (int i = 0; i < productArray.length - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < productArray.length; j++) {
                if (productArray[j].getRating().compareTo(productArray[maxIndex].getRating()) > 0) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                Laptop temp = productArray[i];
                productArray[i] = productArray[maxIndex];
                productArray[maxIndex] = temp;
            }
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the minimum rating threshold: ");
        String ratingThreshold = scanner.nextLine();


        System.out.println("Response are : ");
        for (int index = 0; index<modelLaptop.size();index++){
            if (modelLaptop.get(index).getRating().compareTo(ratingThreshold) > 0){
                System.out.println("ID  : " + modelLaptop.get(index).getId());
                System.out.println("Nama Laptop : " + modelLaptop.get(index).getTitle());
                System.out.println("Deskripsi : "+ modelLaptop.get(index).getDescription());
                System.out.println("Harga : " + modelLaptop.get(index).getPrice());
                System.out.println("Penilaian : " + modelLaptop.get(index).getRating());
                System.out.println("Brand : " + modelLaptop.get(index).getBrand());

            }
        }


    }

}
