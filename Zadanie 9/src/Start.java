import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.*;
import java.util.*;
import java.util.List;

class Start {

    public record Point(Double x, Double y) {

    }


    public static class Window extends JFrame {

        static double centerXFactor;
        static double centerYFactor;
        static double minX;
        static double maxX;
        static double minY;
        static double maxY;
        static int sytuationX = 1;
        static int sytuationY = 1;

        public static class Data {
            private final Set<Point> data = new HashSet<>();
            private String filePath;

            public Data(String filePath) throws FileNotFoundException {
                setFilePath(filePath);
                loadData();
                calculateAll();
            }

            public void setFilePath(String filePath) {
                this.filePath = filePath;
            }

            public Set<Point> getData() {
                return data;
            }

            public void loadData() throws FileNotFoundException {
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                String line;
                try {
                    int PointsLength = Integer.parseInt(reader.readLine().trim());
                    for (int i = 0; i < PointsLength; i++) {
                        line = reader.readLine();
                        if (line == null || line.isEmpty()) {
                            break;
                        }
                        double x = Double.parseDouble(line.split(" ")[0]);
                        double y = Double.parseDouble(line.split(" ")[1]);
                        data.add(new Point(x, y));

                    }
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            private double a;
            private double b;

            public double getA() {
                return a;
            }

            public double getB() {
                return b;
            }

            public void calculateAll() {
                int n = data.size();
                double sumX = 0;
                double sumX2 = 0;
                double sumY = 0;
                double sumXY = 0;

                List<Double> Xlist = new ArrayList<>();
                List<Double> Ylist = new ArrayList<>();
                for (Point point : data) {
                    double x = point.x;
                    Xlist.add(x);
                    double y = point.y;
                    Ylist.add(y);
                    sumX += x;
                    sumX2 += x * x;
                    sumY += y;
                    sumXY += x * y;

                }

                minX = Collections.min(Xlist);
                maxX = Collections.max(Xlist);
                minY = Collections.min(Ylist);
                maxY = Collections.max(Ylist);
                double dataXwidth = Math.abs(minX - maxX);
                double dataYwidth = Math.abs(minY - maxY);
                if (minX < 0 && maxX > 0) {
                    centerXFactor = Math.abs(minX) / dataXwidth;
                    sytuationX = 1;
                } else if (minX >= 0 && maxX > 0) {
                    sytuationX = 2;
                } else if (minX < 0 && maxX <= 0) {
                    sytuationX = 3;
                }

                if (minY < 0 && maxY > 0) {
                    centerYFactor = Math.abs(minY) / dataYwidth;
                    sytuationY = 1;
                } else if (minY >= 0 && maxY > 0) {
                    sytuationY = 2;
                } else if (minY < 0 && maxY <= 0) {
                    sytuationY = 3;
                }

                double w = n * sumX2 - (sumX * sumX);
                this.a = (sumX2 * sumY - sumX * sumXY) / w;
                this.b = (n * sumXY - sumX * sumY) / w;


            }
        }

        private Set<Point> currentPoints;
        private final JLabel labelA = new JLabel("A = ");
        private final JLabel labelB = new JLabel("B = ");
        JButton button1 = new JButton("READFILE");
        private double A;
        private double B;
        private final JPanel PlotPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawPlot((Graphics2D) g);
            }

        };

        public Window() {
            JPanel downPanel = new JPanel();
            JLabel mainPanel = new JLabel();
            setTitle("Regresion");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());
            downPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
            downPanel.add(button1);
            downPanel.add(labelA);
            downPanel.add(labelB);
            mainPanel.setLayout(new BorderLayout());
            mainPanel.setBorder(new EmptyBorder(15, 15, 0, 15));
            mainPanel.add(downPanel, BorderLayout.SOUTH);
            mainPanel.add(PlotPanel, BorderLayout.CENTER);
            add(mainPanel, BorderLayout.CENTER);
            labelA.setForeground(Color.BLACK);
            labelB.setForeground(Color.BLACK);
            button1.setPreferredSize(new Dimension(95, 30));
            button1.setFocusPainted(false);
            button1.setBackground(Color.GREEN);
            button1.setForeground(Color.BLACK);
            button1.addActionListener(e -> {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                if (chooser.showOpenDialog(Window.this) == JFileChooser.APPROVE_OPTION) {
                    File myFile = chooser.getSelectedFile();
                    String currentPath = myFile.getAbsolutePath();
                    try {
                        Data currentData = new Data(currentPath);
                        currentPoints = currentData.getData();
                        A = currentData.getA();
                        B = currentData.getB();
                        labelA.setText("A = " + (int) (A * 10000) / 10000.0);
                        labelB.setText("B = " + (int) (B * 10000) / 10000.0);
                        PlotPanel.repaint();

                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            });
            PlotPanel.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    PlotPanel.repaint();
                }
            });

            pack();
            setLocationRelativeTo(null);
            setExtendedState(MAXIMIZED_BOTH);
            setVisible(true);
        }

        double calculateYfunc(double x) {
            return x * B + A;
        }


        public void drawPlot(Graphics2D g) {

            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            labelA.setText("A = " + A);
            labelB.setText("B = " + B);

            if (currentPoints != null) {
                int blackDiagonal = 12;
                int greenDiagonal = 8;
                int lineWidth = 4;
                int marginCorrect = 40;
                button1.setText("READFILE");
                button1.setPreferredSize(new Dimension(95, 30));
                if (PlotPanel.getWidth() < 410) {
                    labelA.setText("A = " + (int) (A * 10000) / 10000.0);
                    labelB.setText("B = " + (int) (B * 10000) / 10000.0);
                    blackDiagonal = 10;
                    greenDiagonal = 6;
                    lineWidth = 2;
                    marginCorrect = 10;
                    if (PlotPanel.getWidth() < 250) {
                        labelA.setText("A=" + (int) (A * 10000) / 10000.0);
                        labelB.setText("B=" + (int) (B * 10000) / 10000.0);
                        button1.setPreferredSize(new Dimension(50, 20));
                        button1.setText("READ");
                        button1.setMargin(new Insets(2, 2, 2, 2));
                        if (PlotPanel.getWidth() < 190) {
                            labelA.setText("A=" + (int) (A * 100) / 100.0);
                            labelB.setText("B=" + (int) (B * 100) / 100.0);
                        }
                    }
                } else if (PlotPanel.getWidth() < 800) {
                    lineWidth = 3;
                    marginCorrect = 20;
                }
                int width = PlotPanel.getWidth() - 2 * marginCorrect;
                int height = PlotPanel.getHeight() + -2 * marginCorrect;
                double axisMargin = 20;
                int Y0displayed = (int) (centerYFactor * height);
                int X0displayed = (int) (centerXFactor * width);
                switch (sytuationX) {
                    case 1: {
                        X0displayed = (int) (centerXFactor * width);
                        break;
                    }
                    case 2: {
                        X0displayed = (int) axisMargin;
                        break;
                    }
                    case 3: {
                        X0displayed = (int) (width - axisMargin);
                        break;
                    }
                }
                switch (sytuationY) {
                    case 1: {
                        Y0displayed = (int) (height - (centerYFactor * height));
                        break;
                    }
                    case 2: {
                        Y0displayed = (int) (height - axisMargin);
                        break;
                    }
                    case 3: {
                        Y0displayed = (int) axisMargin;
                        break;
                    }
                }
                g.setColor(Color.BLACK);
                g.setStroke(new BasicStroke(2));
                g.drawLine(0, Y0displayed + marginCorrect, width + 2 * marginCorrect, Y0displayed + marginCorrect);
                g.drawLine(X0displayed + marginCorrect, 0, X0displayed + marginCorrect, height + 2 * marginCorrect);
                Polygon arrowX = new Polygon();
                arrowX.addPoint(width + 2 * marginCorrect, Y0displayed + marginCorrect);
                arrowX.addPoint(width + 2 * marginCorrect - 15, Y0displayed + marginCorrect + 4);
                arrowX.addPoint(width + 2 * marginCorrect - 15, Y0displayed + marginCorrect - 4);
                Polygon arrowY = new Polygon();
                arrowY.addPoint(X0displayed + marginCorrect, 0);
                arrowY.addPoint(X0displayed + marginCorrect + 4, 15);
                arrowY.addPoint(X0displayed + marginCorrect - 4, 15);
                g.fillPolygon(arrowX);
                g.drawPolygon(arrowX);
                g.fillPolygon(arrowY);
                g.drawPolygon(arrowY);


                int lineYStart = 0;
                int lineYEnd = 0;
                int lineXStart = 0;
                int lineXEnd = 0;
                double startY = calculateYfunc(maxX);
                double endY = calculateYfunc(minX);

                switch (sytuationY) {
                    case 1: {
                        if (startY > 0) {
                            lineYEnd = Y0displayed - (int) (startY / maxY * Y0displayed);

                        } else if (startY == 0) {
                            lineYEnd = Y0displayed;
                        } else if (startY < 0) {
                            lineYEnd = (int) (startY / minY * (height - Y0displayed)) + Y0displayed;
                        }
                        break;
                    }
                    case 2: {
                        if (startY == 0) {
                            lineYEnd = Y0displayed;
                        } else {

                            lineYEnd = Y0displayed - (int) (startY / maxY * Y0displayed);
                        }
                        break;
                    }
                    case 3: {
                        if (startY == 0) {
                            lineYEnd = Y0displayed;
                        } else {
                            lineYEnd = (int) (startY / minY * (height - Y0displayed)) + Y0displayed;
                        }
                    }

                }
                switch (sytuationY) {
                    case 1: {
                        if (endY > 0) {
                            lineYStart = Y0displayed - (int) (endY / maxY * Y0displayed);

                        } else if (endY == 0) {
                            lineYStart = Y0displayed;
                        } else if (endY < 0) {
                            lineYStart = (int) (endY / minY * (height - Y0displayed)) + Y0displayed;
                        }
                        break;
                    }
                    case 2: {
                        if (endY == 0) {
                            lineYStart = Y0displayed;
                        } else {

                            lineYStart = Y0displayed - (int) (endY / maxY * Y0displayed);
                        }
                        break;
                    }
                    case 3: {
                        if (endY == 0) {
                            lineYStart = Y0displayed;
                        } else {
                            lineYStart = (int) (endY / minY * (height - Y0displayed)) + Y0displayed;
                        }
                    }

                }
                switch (sytuationX) {
                    case 1: {
                        if (minX > 0) {
                            lineXStart = (int) (minX / maxX * (width - X0displayed) + X0displayed);
                        } else if (minX == 0) {
                            lineXStart = X0displayed;
                        } else if (minX < 0) {
                            lineXStart = (int) (X0displayed - 1.0 * X0displayed);
                        }
                        break;
                    }
                    case 2: {
                        if (minX == 0) {
                            lineXStart = X0displayed;
                        } else {
                            lineXStart = (int) (minX / maxX * (width - X0displayed) + X0displayed);
                        }
                        break;
                    }
                    case 3: {
                        if (minX == 0) {
                            lineXStart = X0displayed;
                        } else {
                            lineXStart = (int) (X0displayed - 1.0 * X0displayed);
                        }
                        break;
                    }
                }
                switch (sytuationX) {
                    case 1: {
                        if (maxX > 0) {
                            lineXEnd = (int) (1.0 * (width - X0displayed) + X0displayed);
                        } else if (maxX == 0) {
                            lineXEnd = X0displayed;
                        } else if (maxX < 0) {
                            lineXEnd = (int) (X0displayed - maxX / minX * X0displayed);
                        }
                        break;
                    }
                    case 2: {
                        if (maxX == 0) {
                            lineXEnd = X0displayed;
                        } else {
                            lineXEnd = (int) (1.0 * (width - X0displayed) + X0displayed);
                        }
                        break;
                    }
                    case 3: {
                        if (maxX == 0) {
                            lineXEnd = X0displayed;
                        } else {
                            lineXEnd = (int) (X0displayed - maxX / minX * X0displayed);
                        }
                        break;
                    }
                }
                g.setColor(Color.BLUE);
                g.setStroke(new BasicStroke(lineWidth));
                g.drawLine(lineXStart + marginCorrect, lineYStart + marginCorrect, lineXEnd + marginCorrect, lineYEnd + marginCorrect);


                for (Point point : currentPoints) {
                    double x = point.x;
                    double y = point.y;
                    int xPixels = 0;
                    int yPixels = 0;


                    switch (sytuationX) {
                        case 1: {
                            if (x > 0) {
                                xPixels = (int) (x / maxX * (width - X0displayed) + X0displayed);
                            } else if (x == 0) {
                                xPixels = X0displayed;
                            } else if (x < 0) {
                                xPixels = (int) (X0displayed - x / minX * X0displayed);
                            }
                            break;
                        }
                        case 2: {
                            if (x == 0) {
                                xPixels = X0displayed;
                            } else {
                                xPixels = (int) (x / maxX * (width - X0displayed) + X0displayed);
                            }
                            break;
                        }
                        case 3: {
                            if (x == 0) {
                                xPixels = X0displayed;
                            } else {
                                xPixels = (int) (X0displayed - x / minX * X0displayed);
                            }
                            break;
                        }
                    }

                    switch (sytuationY) {
                        case 1: {
                            if (y > 0) {
                                yPixels = Y0displayed - (int) (y / maxY * Y0displayed);

                            } else if (y == 0) {
                                yPixels = Y0displayed;
                            } else if (y < 0) {
                                yPixels = (int) (y / minY * (height - Y0displayed)) + Y0displayed;
                            }
                            break;
                        }
                        case 2: {
                            if (y == 0) {
                                yPixels = Y0displayed;
                            } else {

                                yPixels = Y0displayed - (int) (y / maxY * Y0displayed);
                            }
                            break;
                        }
                        case 3: {
                            if (y == 0) {
                                yPixels = Y0displayed;
                            } else {
                                yPixels = (int) (y / minY * (height - Y0displayed)) + Y0displayed;
                            }
                        }
                    }
                    g.setColor(Color.BLACK);
                    g.fillOval(xPixels - blackDiagonal / 2 + marginCorrect, yPixels - blackDiagonal / 2 + marginCorrect, blackDiagonal, blackDiagonal);
                    g.setColor(Color.GREEN);
                    g.fillOval(xPixels - greenDiagonal / 2 + marginCorrect, yPixels - greenDiagonal / 2 + marginCorrect, greenDiagonal, greenDiagonal);
                }

            }

        }

    }

    public static void main(String[] argv) {

        SwingUtilities.invokeLater(() -> new Window());
    }


}