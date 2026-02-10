package database;

import model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClothingDAO {

    // INSERT SHIRT
    public boolean insertShirt(Shirt shirt) {
        String sql = "INSERT INTO items (name, price, item_type, size_val) VALUES (?, ?, 'SHIRT', ?)";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database connection failed!");
            return false;
        }

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, shirt.getName());
            stmt.setDouble(2, shirt.getPrice());
            stmt.setString(3, shirt.getSize());

            int rowsInserted = stmt.executeUpdate();
            stmt.close();

            if (rowsInserted > 0) {
                System.out.println("Shirt added successfully: " + shirt.getName());
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error adding shirt!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return false;
    }

    // INSERT JACKET
    public boolean insertJacket(Jacket jacket) {
        String sql = "INSERT INTO items (name, price, item_type, fabric_val) VALUES (?, ?, 'JACKET', ?)";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("Database connection failed!");
            return false;
        }

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, jacket.getName());
            stmt.setDouble(2, jacket.getPrice());
            stmt.setString(3, jacket.getFabric());

            int rowsInserted = stmt.executeUpdate();
            stmt.close();

            if (rowsInserted > 0) {
                System.out.println("Jacket added successfully: " + jacket.getName());
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error adding jacket!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return false;
    }

    // GET ALL ITEMS*
    public List<ClothingItem> getAllItems() {
        List<ClothingItem> items = new ArrayList<>();
        String sql = "SELECT * FROM items ORDER BY id";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return items;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                ClothingItem item = extractItemFromResultSet(rs);
                if (item != null) {
                    items.add(item);
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error loading items!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return items;
    }

    // GET ALL SHIRTS
    public List<Shirt> getAllShirts() {
        List<Shirt> shirts = new ArrayList<>();
        String sql = "SELECT * FROM items WHERE item_type = 'SHIRT' ORDER BY id";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return shirts;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String size = rs.getString("size_val");
                shirts.add(new Shirt(id, name, price, size));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error loading shirts!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return shirts;
    }

    // GET ALL JACKETS
    public List<Jacket> getAllJackets() {
        List<Jacket> jackets = new ArrayList<>();
        String sql = "SELECT * FROM items WHERE item_type = 'JACKET' ORDER BY id";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return jackets;

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String fabric = rs.getString("fabric_val");
                jackets.add(new Jacket(id, name, price, fabric));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error loading jackets!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return jackets;
    }

    // GET ITEM BY ID
    public ClothingItem getItemById(int itemId) {
        String sql = "SELECT * FROM items WHERE id = ?";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return null;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ClothingItem item = extractItemFromResultSet(rs);
                rs.close();
                stmt.close();
                return item;
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error finding item!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return null;
    }

    // DISPLAY ALL ITEMS
    public void displayAllItems() {
        List<ClothingItem> items = getAllItems();

        if (items.isEmpty()) {
            System.out.println("No items in database!");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("        ALL CLOTHING ITEMS");
        System.out.println("========================================");
        for (ClothingItem item : items) {
            item.displayInfo();
        }
        System.out.println("========================================");
        System.out.println("Total items: " + items.size());
    }

    // UPDATE SHIRT
    public boolean updateShirt(Shirt shirt) {
        String sql = "UPDATE items SET name = ?, price = ?, size_val = ? " +
                "WHERE id = ? AND item_type = 'SHIRT'";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, shirt.getName());
            stmt.setDouble(2, shirt.getPrice());
            stmt.setString(3, shirt.getSize());
            stmt.setInt(4, shirt.getId());

            int rowsUpdated = stmt.executeUpdate();
            stmt.close();

            if (rowsUpdated > 0) {
                System.out.println("Shirt updated successfully: " + shirt.getName());
                return true;
            } else {
                System.out.println("No shirt found with ID: " + shirt.getId());
            }
        } catch (SQLException e) {
            System.out.println("Update failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return false;
    }

    // UPDATE JACKET
    public boolean updateJacket(Jacket jacket) {
        String sql = "UPDATE items SET name = ?, price = ?, fabric_val = ? " +
                "WHERE id = ? AND item_type = 'JACKET'";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, jacket.getName());
            stmt.setDouble(2, jacket.getPrice());
            stmt.setString(3, jacket.getFabric());
            stmt.setInt(4, jacket.getId());

            int rowsUpdated = stmt.executeUpdate();
            stmt.close();

            if (rowsUpdated > 0) {
                System.out.println("Jacket updated successfully: " + jacket.getName());
                return true;
            } else {
                System.out.println("No jacket found with ID: " + jacket.getId());
            }
        } catch (SQLException e) {
            System.out.println("Update failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return false;
    }

    // DELETE ITEM
    public boolean deleteItem(int itemId) {
        String sql = "DELETE FROM items WHERE id = ?";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return false;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, itemId);

            int rowsDeleted = stmt.executeUpdate();
            stmt.close();

            if (rowsDeleted > 0) {
                System.out.println("Item deleted successfully (ID: " + itemId + ")");
                return true;
            } else {
                System.out.println("No item found with ID: " + itemId);
            }
        } catch (SQLException e) {
            System.out.println("Delete failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }
        return false;
    }

    // SEARCH BY NAME*
    public List<ClothingItem> searchByName(String name) {
        List<ClothingItem> results = new ArrayList<>();
        // ILIKE = case-insensitive search (PostgreSQL)
        // % = wildcard (matches any characters)
        String sql = "SELECT * FROM items WHERE name ILIKE ? ORDER BY name";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return results;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + name + "%"); // Add wildcards for partial match

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ClothingItem item = extractItemFromResultSet(rs);
                if (item != null) {
                    results.add(item);
                }
            }

            rs.close();
            stmt.close();
            System.out.println("Found " + results.size() + " item(s) matching '" + name + "'");
        } catch (SQLException e) {
            System.out.println("Search failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return results;
    }

    // SEARCH BY PRICE RANGE*
    public List<ClothingItem> searchByPriceRange(double minPrice, double maxPrice) {
        List<ClothingItem> results = new ArrayList<>();
        // BETWEEN includes both min and max values
        String sql = "SELECT * FROM items WHERE price BETWEEN ? AND ? ORDER BY price DESC";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return results;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, minPrice);
            stmt.setDouble(2, maxPrice);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ClothingItem item = extractItemFromResultSet(rs);
                if (item != null) {
                    results.add(item);
                }
            }
            rs.close();
            stmt.close();
            System.out.println("Found " + results.size() + " item(s) in price range " +
                    minPrice + " - " + maxPrice + " KZT");
        } catch (SQLException e) {
            System.out.println("Search failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return results;
    }

    // SEARCH BY MIN PRICE
    public List<ClothingItem> searchByMinPrice(double minPrice) {
        List<ClothingItem> results = new ArrayList<>();
        String sql = "SELECT * FROM items WHERE price >= ? ORDER BY price DESC";

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) return results;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, minPrice);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ClothingItem item = extractItemFromResultSet(rs);
                if (item != null) {
                    results.add(item);
                }
            }

            rs.close();
            stmt.close();
            System.out.println("Found " + results.size() + " item(s) with price >= " + minPrice + " KZT");
        } catch (SQLException e) {
            System.out.println("Search failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(conn);
        }

        return results;
    }

    // HELPER: EXTRACT ITEM FROM RESULTSET
    private ClothingItem extractItemFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        double price = rs.getDouble("price");
        String type = rs.getString("item_type");

        if ("SHIRT".equals(type)) {
            String size = rs.getString("size_val");
            return new Shirt(id, name, price, size);
        } else if ("JACKET".equals(type)) {
            String fabric = rs.getString("fabric_val");
            return new Jacket(id, name, price, fabric);
        }
        return null;
    }
}