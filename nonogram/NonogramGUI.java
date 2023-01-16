/**
* A gui user interface to a Nonogram puzzle.
* 
* @author OTechCup
* @credits ["Mr. O"]
* @version 0.1
*/


package nonogram;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.FileNotFoundException;
import java.io.File;

import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;


public class NonogramGUI extends JFrame {
    
    /**
    * Main constructor
    */
    public NonogramGUI() {
        NonogramPuzzle(); // initialize nonogram puzzle
        GUIFrame(); // initialize the gui window
    }
    
    
    /**
    * Create a GUI window
    */
    private void GUIFrame() {
        this.setVisible(true); 
        this.setSize(420, 480); 
        this.setTitle("Nonogram"); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setResizable(false); 
        this.getContentPane().setBackground(Color.BLACK); 
        
        GUIMenu(); // initialize gui menu
        NonogramBoard(); // initialize nonogram board
        DialogueBox(); // initialize dialogue box
        
        messageBox.setText("Start the Game... \nClick on the white cells to make a play...");
    }

    
    /**
    * Create a GUI menu
    */
    private void GUIMenu() {
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.BLACK);
        
        FlowLayout flowLayout = (FlowLayout) menuPanel.getLayout(); 
        flowLayout.setAlignment(FlowLayout.CENTER); 
        flowLayout.setVgap(10); 
        
        this.getContentPane().add(menuPanel, BorderLayout.NORTH); 
        
        JButton saveButton = new JButton("Save");
        menuPanel.add(saveButton);
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String msg = puzzle.save(); // Save the current state of the puzzle
                
                messageBox.setText(msg);
            }
        });
            
        JButton loadButton = new JButton("Load"); 
        menuPanel.add(loadButton);
        loadButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
        });
            
        JButton undoButton = new JButton("Undo"); 
        menuPanel.add(undoButton);
        undoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
        });
            
        JButton restartButton = new JButton("Restart"); 
        menuPanel.add(restartButton); 
        restartButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
        });
        
        JButton helpButton = new JButton("Help"); 
        menuPanel.add(helpButton); 
        helpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                messageBox.setText(
                    "Nonogram is a puzzle where you must color in/fill in the grid according to the patterns of contiguous full cells given in the rows and columns. Full cells are shown as '" + FULL_CHAR + "', unknown cells as 'Blank Cell' and cells you are sure are empty as '" + EMPTY_CHAR + "'. If a row or column is invalid (doesn't match the pattern) this will be marked with a 'Red Color', a solved row or column is marked with a 'Green Color', but it may still be wrong because of the other columns or rows - keep trying!"
                );
            }
        });
    }
    
    
    /**
    * Nonogram board. This create the nonogram puzzle board with 
    * both the cells and lables
    */
    private void NonogramBoard() {
        // collect the nums for the rows and columns
        int      numRows      = puzzle.getNumRows();
        int      numCols      = puzzle.getNumCols();
        int[][] rowNums       = new int[numRows][];
        int[][] colNums       = new int[numCols][];
        
        for (int row=0; row<numRows; row++) {
            rowNums[row] = puzzle.getRowNums(row);
        }
        
        for (int col=0; col<numRows; col++) {
            colNums[col] = puzzle.getColNums(col);
        }        
        
        JPanel boardPanel = new JPanel();
        boardPanel.setBackground(Color.BLACK);
        this.getContentPane().add(boardPanel, BorderLayout.CENTER);
        boardPanel.setLayout(new CardLayout(20, 10));
        
        JPanel boardPanelTileContainer = new JPanel();
        boardPanelTileContainer.setBackground(Color.BLACK);
        boardPanel.add(boardPanelTileContainer);
        boardPanelTileContainer.setLayout(new GridLayout(6, 6, 2, 2));
        
        JPanel empty_tile = new JPanel();
        empty_tile.setBackground(Color.BLACK);
        boardPanelTileContainer.add(empty_tile);
        empty_tile.setLayout(new BorderLayout(0, 0));
        
        JPanel top_label_1 = new JPanel();
        top_label_1.setBackground(new Color(33, 222, 222));
        boardPanelTileContainer.add(top_label_1);
        top_label_1.setLayout(new CardLayout(17, 10));
        
        JTextPane topLabel_1_text = new JTextPane();
        topLabel_1_text.setEditable(false);
        topLabel_1_text.setFont(new Font("Tahoma", Font.BOLD, 11));
        topLabel_1_text.setBackground(new Color(33, 222, 222));
        topLabel_1_text.setText(String.valueOf(colNums[0][0]));
        top_label_1.add(topLabel_1_text);
        
        JPanel top_label_2 = new JPanel();
        top_label_2.setBackground(new Color(33, 222, 222));
        boardPanelTileContainer.add(top_label_2);
        top_label_2.setLayout(new CardLayout(17, 10));
        
        JTextPane topLabel_2_text = new JTextPane();
        topLabel_2_text.setEditable(false);
        topLabel_2_text.setFont(new Font("Tahoma", Font.BOLD, 11));
        topLabel_2_text.setBackground(new Color(33, 222, 222));
        topLabel_2_text.setText(String.valueOf(colNums[1][0]));
        top_label_2.add(topLabel_2_text);
        
        JPanel top_label_3 = new JPanel();
        top_label_3.setBackground(new Color(33, 222, 222));
        boardPanelTileContainer.add(top_label_3);
        top_label_3.setLayout(new CardLayout(17, 10));
        
        JTextPane topLabel_3_text = new JTextPane();
        topLabel_3_text.setEditable(false);
        topLabel_3_text.setBackground(new Color(33, 222, 222));
        topLabel_3_text.setFont(new Font("Tahoma", Font.BOLD, 11));
        topLabel_3_text.setText(String.valueOf(colNums[2][0]));
        top_label_3.add(topLabel_3_text);
        
        JPanel top_label_4 = new JPanel();
        top_label_4.setBackground(new Color(33, 222, 222));
        boardPanelTileContainer.add(top_label_4);
        top_label_4.setLayout(new CardLayout(17, 10));
        
        JTextPane topLabel_4_text = new JTextPane();
        topLabel_4_text.setEditable(false);
        topLabel_4_text.setFont(new Font("Tahoma", Font.BOLD, 11));
        topLabel_4_text.setText(String.valueOf(colNums[3][0]));
        topLabel_4_text.setBackground(new Color(33, 222, 222));
        top_label_4.add(topLabel_4_text);
        
        JPanel top_label_5 = new JPanel();
        top_label_5.setBackground(new Color(33, 222, 222));
        boardPanelTileContainer.add(top_label_5);
        top_label_5.setLayout(new CardLayout(17, 2));
        
        JTextPane topLabel_5_text = new JTextPane();
        topLabel_5_text.setEditable(false);
        topLabel_5_text.setBackground(new Color(33, 222, 222));
        topLabel_5_text.setFont(new Font("Tahoma", Font.BOLD, 11));
        topLabel_5_text.setText(String.valueOf(colNums[4][0]) + "\n" + String.valueOf(colNums[4][1]));
        top_label_5.add(topLabel_5_text);
        
        JPanel left_label_1 = new JPanel();
        left_label_1.setBackground(new Color(33, 222, 222));
        boardPanelTileContainer.add(left_label_1);
        left_label_1.setLayout(new CardLayout(13, 10));
        
        JTextPane leftLabel_1_text = new JTextPane();
        leftLabel_1_text.setBackground(new Color(33, 222, 222));
        leftLabel_1_text.setEditable(false);
        leftLabel_1_text.setFont(new Font("Tahoma", Font.BOLD, 11));
        leftLabel_1_text.setText(String.valueOf(rowNums[0][0]) + " " + String.valueOf(rowNums[0][1]));
        left_label_1.add(leftLabel_1_text);
        
        JPanel cell_1 = new JPanel();
        cell_1.setBackground(new Color(255, 255, 255));
        cell_1.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_1);
        cell_1.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_1 = new JButton("");
        cellButton_1.setForeground(new Color(0, 0, 255));
        cellButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_1.setBackground(new Color(255, 255, 255));
        cell_1.add(cellButton_1, BorderLayout.CENTER);
        cellButton_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_1.setText(String.valueOf(moveState));
                } else {
                    cellButton_1.setText("");
                }
                
                char m = move();
                int btnRow = 0;
                int btnCol = 0;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel cell_2 = new JPanel();
        cell_2.setBackground(new Color(255, 255, 255));
        cell_2.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_2);
        cell_2.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_2 = new JButton("");
        cellButton_2.setForeground(new Color(0, 0, 255));
        cellButton_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_2.setBackground(new Color(255, 255, 255));
        cell_2.add(cellButton_2, BorderLayout.CENTER);
        cellButton_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_2.setText(String.valueOf(moveState));
                } else {
                    cellButton_2.setText("");
                }
                
                char m = move();
                int btnRow = 0;
                int btnCol = 1;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel cell_3 = new JPanel();
        cell_3.setBackground(new Color(255, 255, 255));
        cell_3.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_3);
        cell_3.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_3 = new JButton("");
        cellButton_3.setForeground(new Color(0, 0, 255));
        cellButton_3.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_3.setBackground(new Color(255, 255, 255));
        cell_3.add(cellButton_3, BorderLayout.CENTER);
        cellButton_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_3.setText(String.valueOf(moveState));
                } else {
                    cellButton_3.setText("");
                }
                
                char m = move();
                int btnRow = 0;
                int btnCol = 2;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel cell_4 = new JPanel();
        cell_4.setBackground(new Color(255, 255, 255));
        cell_4.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_4);
        cell_4.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_4 = new JButton("");
        cellButton_4.setForeground(new Color(0, 0, 255));
        cellButton_4.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_4.setBackground(new Color(255, 255, 255));
        cell_4.add(cellButton_4, BorderLayout.CENTER);
        cellButton_4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_4.setText(String.valueOf(moveState));
                } else {
                    cellButton_4.setText("");
                }
                
                char m = move();
                int btnRow = 0;
                int btnCol = 3;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel cell_5 = new JPanel();
        cell_5.setBackground(new Color(255, 255, 255));
        cell_5.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_5);
        cell_5.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_5 = new JButton("");
        cellButton_5.setForeground(new Color(0, 0, 255));
        cellButton_5.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_5.setBackground(new Color(255, 255, 255));
        cell_5.add(cellButton_5, BorderLayout.CENTER);
        cellButton_5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_5.setText(String.valueOf(moveState));
                } else {
                    cellButton_5.setText("");
                }
                
                char m = move();
                int btnRow = 0;
                int btnCol = 4;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel left_label_2 = new JPanel();
        left_label_2.setBackground(new Color(33, 222, 222));
        boardPanelTileContainer.add(left_label_2);
        left_label_2.setLayout(new CardLayout(13, 10));
        
        JTextPane leftLabel_2_text = new JTextPane();
        leftLabel_2_text.setBackground(new Color(33, 222, 222));
        leftLabel_2_text.setFont(new Font("Tahoma", Font.BOLD, 11));
        leftLabel_2_text.setText(String.valueOf(rowNums[1][0]) + " " + String.valueOf(rowNums[1][1]));
        leftLabel_2_text.setEditable(false);
        left_label_2.add(leftLabel_2_text);
        
        JPanel cell_6 = new JPanel();
        cell_6.setBackground(new Color(255, 255, 255));
        cell_6.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_6);
        cell_6.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_6 = new JButton("");
        cellButton_6.setForeground(new Color(0, 0, 255));
        cellButton_6.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_6.setBackground(new Color(255, 255, 255));
        cell_6.add(cellButton_6, BorderLayout.CENTER);
        cellButton_6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_6.setText(String.valueOf(moveState));
                } else {
                    cellButton_6.setText("");
                }
                
                char m = move();
                int btnRow = 1;
                int btnCol = 0;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel cell_7 = new JPanel();
        cell_7.setBackground(new Color(255, 255, 255));
        cell_7.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_7);
        cell_7.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_7 = new JButton("");
        cellButton_7.setForeground(new Color(0, 0, 255));
        cellButton_7.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_7.setBackground(new Color(255, 255, 255));
        cell_7.add(cellButton_7, BorderLayout.CENTER);
        cellButton_7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_7.setText(String.valueOf(moveState));
                } else {
                    cellButton_7.setText("");
                }
                
                char m = move();
                int btnRow = 1;
                int btnCol = 1;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel cell_8 = new JPanel();
        cell_8.setBackground(new Color(255, 255, 255));
        cell_8.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_8);
        cell_8.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_8 = new JButton("");
        cellButton_8.setForeground(new Color(0, 0, 255));
        cellButton_8.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_8.setBackground(new Color(255, 255, 255));
        cell_8.add(cellButton_8, BorderLayout.CENTER);
        cellButton_8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_8.setText(String.valueOf(moveState));
                } else {
                    cellButton_8.setText("");
                }
                
                char m = move();
                int btnRow = 1;
                int btnCol = 2;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel cell_9 = new JPanel();
        cell_9.setBackground(new Color(255, 255, 255));
        cell_9.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_9);
        cell_9.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_9 = new JButton("");
        cellButton_9.setForeground(new Color(0, 0, 255));
        cellButton_9.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_9.setBackground(new Color(255, 255, 255));
        cell_9.add(cellButton_9, BorderLayout.CENTER);
        cellButton_9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_9.setText(String.valueOf(moveState));
                } else {
                    cellButton_9.setText("");
                }
                
                char m = move();
                int btnRow = 1;
                int btnCol = 3;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel cell_10 = new JPanel();
        cell_10.setBackground(new Color(255, 255, 255));
        cell_10.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_10);
        cell_10.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_10 = new JButton("");
        cellButton_10.setForeground(new Color(0, 0, 255));
        cellButton_10.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_10.setBackground(new Color(255, 255, 255));
        cell_10.add(cellButton_10, BorderLayout.CENTER);
        cellButton_10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_10.setText(String.valueOf(moveState));
                } else {
                    cellButton_10.setText("");
                }
                
                char m = move();
                int btnRow = 1;
                int btnCol = 4;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel left_label_3 = new JPanel();
        left_label_3.setBackground(new Color(33, 222, 222));
        boardPanelTileContainer.add(left_label_3);
        left_label_3.setLayout(new CardLayout(17, 10));
        
        JTextPane leftLabel_3_text = new JTextPane();
        leftLabel_3_text.setEditable(false);
        leftLabel_3_text.setBackground(new Color(33, 222, 222));
        leftLabel_3_text.setFont(new Font("Tahoma", Font.BOLD, 11));
        leftLabel_3_text.setText(String.valueOf(rowNums[2][0]));
        left_label_3.add(leftLabel_3_text);
        
        JPanel cell_11 = new JPanel();
        cell_11.setBackground(new Color(255, 255, 255));
        cell_11.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_11);
        cell_11.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_11 = new JButton("");
        cellButton_11.setForeground(new Color(0, 0, 255));
        cellButton_11.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_11.setBackground(new Color(255, 255, 255));
        cell_11.add(cellButton_11, BorderLayout.CENTER);
        cellButton_11.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_11.setText(String.valueOf(moveState));
                } else {
                    cellButton_11.setText("");
                }
                
                char m = move();
                int btnRow = 2;
                int btnCol = 0;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel cell_12 = new JPanel();
        cell_12.setBackground(new Color(255, 255, 255));
        cell_12.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_12);
        cell_12.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_12 = new JButton("");
        cellButton_12.setForeground(new Color(0, 0, 255));
        cellButton_12.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_12.setBackground(new Color(255, 255, 255));
        cell_12.add(cellButton_12, BorderLayout.CENTER);
        cellButton_12.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_12.setText(String.valueOf(moveState));
                } else {
                    cellButton_12.setText("");
                }
                
                char m = move();
                int btnRow = 2;
                int btnCol = 1;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel cell_13 = new JPanel();
        cell_13.setBackground(new Color(255, 255, 255));
        cell_13.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_13);
        cell_13.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_13 = new JButton("");
        cellButton_13.setForeground(new Color(0, 0, 255));
        cellButton_13.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_13.setBackground(new Color(255, 255, 255));
        cell_13.add(cellButton_13, BorderLayout.CENTER);
        cellButton_13.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_13.setText(String.valueOf(moveState));
                } else {
                    cellButton_13.setText("");
                }
                
                char m = move();
                int btnRow = 2;
                int btnCol = 2;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel cell_14 = new JPanel();
        cell_14.setBackground(new Color(255, 255, 255));
        cell_14.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_14);
        cell_14.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_14 = new JButton("");
        cellButton_14.setForeground(new Color(0, 0, 255));
        cellButton_14.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_14.setBackground(new Color(255, 255, 255));
        cell_14.add(cellButton_14, BorderLayout.CENTER);
        cellButton_14.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_14.setText(String.valueOf(moveState));
                } else {
                    cellButton_14.setText("");
                }
                
                char m = move();
                int btnRow = 2;
                int btnCol = 3;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel cell_15 = new JPanel();
        cell_15.setBackground(new Color(255, 255, 255));
        cell_15.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_15);
        cell_15.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_15 = new JButton("");
        cellButton_15.setForeground(new Color(0, 0, 255));
        cellButton_15.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_15.setBackground(new Color(255, 255, 255));
        cell_15.add(cellButton_15, BorderLayout.CENTER);
        cellButton_15.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_15.setText(String.valueOf(moveState));
                } else {
                    cellButton_15.setText("");
                }
                
                char m = move();
                int btnRow = 2;
                int btnCol = 4;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel left_label_4 = new JPanel();
        left_label_4.setBackground(new Color(33, 222, 222));
        boardPanelTileContainer.add(left_label_4);
        left_label_4.setLayout(new CardLayout(8, 10));
        
        JTextPane leftLabel_4_text = new JTextPane();
        leftLabel_4_text.setBackground(new Color(33, 222, 222));
        leftLabel_4_text.setEditable(false);
        leftLabel_4_text.setFont(new Font("Tahoma", Font.BOLD, 11));
        leftLabel_4_text.setText(
            String.valueOf(rowNums[3][0]) + " " + String.valueOf(rowNums[3][1]) + " " + String.valueOf(rowNums[3][2])
        );
        left_label_4.add(leftLabel_4_text);
        
        JPanel cell_16 = new JPanel();
        cell_16.setBackground(new Color(255, 255, 255));
        cell_16.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_16);
        cell_16.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_16 = new JButton("");
        cellButton_16.setForeground(new Color(0, 0, 255));
        cellButton_16.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_16.setBackground(new Color(255, 255, 255));
        cell_16.add(cellButton_16, BorderLayout.CENTER);
        cellButton_16.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_16.setText(String.valueOf(moveState));
                } else {
                    cellButton_16.setText("");
                }
                
                char m = move();
                int btnRow = 3;
                int btnCol = 0;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel cell_17 = new JPanel();
        cell_17.setBackground(new Color(255, 255, 255));
        cell_17.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_17);
        cell_17.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_17 = new JButton("");
        cellButton_17.setForeground(new Color(0, 0, 255));
        cellButton_17.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_17.setBackground(new Color(255, 255, 255));
        cell_17.add(cellButton_17, BorderLayout.CENTER);
        cellButton_17.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_17.setText(String.valueOf(moveState));
                } else {
                    cellButton_17.setText("");
                }
                
                char m = move();
                int btnRow = 3;
                int btnCol = 1;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel cell_18 = new JPanel();
        cell_18.setBackground(new Color(255, 255, 255));
        cell_18.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_18);
        cell_18.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_18 = new JButton("");
        cellButton_18.setForeground(new Color(0, 0, 255));
        cellButton_18.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_18.setBackground(new Color(255, 255, 255));
        cell_18.add(cellButton_18, BorderLayout.CENTER);
        cellButton_18.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_18.setText(String.valueOf(moveState));
                } else {
                    cellButton_18.setText("");
                }
                
                char m = move();
                int btnRow = 3;
                int btnCol = 2;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel cell_19 = new JPanel();
        cell_19.setBackground(new Color(255, 255, 255));
        cell_19.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_19);
        cell_19.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_19 = new JButton("");
        cellButton_19.setForeground(new Color(0, 0, 255));
        cellButton_19.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_19.setBackground(new Color(255, 255, 255));
        cell_19.add(cellButton_19, BorderLayout.CENTER);
        cellButton_19.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_19.setText(String.valueOf(moveState));
                } else {
                    cellButton_19.setText("");
                }
                
                char m = move();
                int btnRow = 3;
                int btnCol = 3;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel cell_20 = new JPanel();
        cell_20.setBackground(new Color(255, 255, 255));
        cell_20.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_20);
        cell_20.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_20 = new JButton("");
        cellButton_20.setForeground(new Color(0, 0, 255));
        cellButton_20.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_20.setBackground(new Color(255, 255, 255));
        cell_20.add(cellButton_20, BorderLayout.CENTER);
        cellButton_20.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_20.setText(String.valueOf(moveState));
                } else {
                    cellButton_20.setText("");
                }
                
                char m = move();
                int btnRow = 3;
                int btnCol = 4;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel left_label_5 = new JPanel();
        left_label_5.setBackground(new Color(33, 222, 222));
        boardPanelTileContainer.add(left_label_5);
        left_label_5.setLayout(new CardLayout(17, 10));
        
        JTextPane leftLabel_5_text = new JTextPane();
        leftLabel_5_text.setBackground(new Color(33, 222, 222));
        leftLabel_5_text.setEditable(false);
        leftLabel_5_text.setFont(new Font("Tahoma", Font.BOLD, 11));
        leftLabel_5_text.setText(String.valueOf(rowNums[4][0]));
        left_label_5.add(leftLabel_5_text);
        
        JPanel cell_21 = new JPanel();
        cell_21.setBackground(new Color(255, 255, 255));
        cell_21.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_21);
        cell_21.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_21 = new JButton("");
        cellButton_21.setForeground(new Color(0, 0, 255));
        cellButton_21.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_21.setBackground(new Color(255, 255, 255));
        cell_21.add(cellButton_21, BorderLayout.CENTER);
        cellButton_21.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_21.setText(String.valueOf(moveState));
                } else {
                    cellButton_21.setText("");
                }
                
                char m = move();
                int btnRow = 4;
                int btnCol = 0;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel cell_22 = new JPanel();
        cell_22.setBackground(new Color(255, 255, 255));
        cell_22.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_22);
        cell_22.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_22 = new JButton("");
        cellButton_22.setForeground(new Color(0, 0, 255));
        cellButton_22.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_22.setBackground(new Color(255, 255, 255));
        cell_22.add(cellButton_22, BorderLayout.CENTER);
        cellButton_22.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_22.setText(String.valueOf(moveState));
                } else {
                    cellButton_22.setText("");
                }
                
                char m = move();
                int btnRow = 4;
                int btnCol = 1;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel cell_23 = new JPanel();
        cell_23.setBackground(new Color(255, 255, 255));
        cell_23.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_23);
        cell_23.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_23 = new JButton("");
        cellButton_23.setForeground(new Color(0, 0, 255));
        cellButton_23.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_23.setBackground(new Color(255, 255, 255));
        cell_23.add(cellButton_23, BorderLayout.CENTER);
        cellButton_23.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_23.setText(String.valueOf(moveState));
                } else {
                    cellButton_23.setText("");
                }
                
                char m = move();
                int btnRow = 4;
                int btnCol = 2;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel cell_24 = new JPanel();
        cell_24.setBackground(new Color(255, 255, 255));
        cell_24.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_24);
        cell_24.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_24 = new JButton("");
        cellButton_24.setForeground(new Color(0, 0, 255));
        cellButton_24.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_24.setBackground(new Color(255, 255, 255));
        cell_24.add(cellButton_24, BorderLayout.CENTER);
        cellButton_24.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_24.setText(String.valueOf(moveState));
                } else {
                    cellButton_24.setText("");
                }
                
                char m = move();
                int btnRow = 4;
                int btnCol = 3;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
        
        JPanel cell_25 = new JPanel();
        cell_25.setBackground(new Color(255, 255, 255));
        cell_25.setBorder(new LineBorder(new Color(255, 0, 0), 2));
        boardPanelTileContainer.add(cell_25);
        cell_25.setLayout(new BorderLayout(0, 0));
        
        JButton cellButton_25 = new JButton("");
        cellButton_25.setForeground(new Color(0, 0, 255));
        cellButton_25.setFont(new Font("Tahoma", Font.BOLD, 15));
        cellButton_25.setBackground(new Color(255, 255, 255));
        cell_25.add(cellButton_25, BorderLayout.CENTER);
        cellButton_25.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (moveState != UNKNOWN_CHAR) {
                    cellButton_25.setText(String.valueOf(moveState));
                } else {
                    cellButton_25.setText("");
                }
                
                char m = move();
                int btnRow = 4;
                int btnCol = 4;
                
                getUserMove(m, btnRow, btnCol);
            }
        });
    }
    
    
    /**
    * Dialogue box. This create a dialogue box for updating the player
    * on certain information or it's used to send messages to the player.
    */
    private void DialogueBox() {
        JPanel dialogueBoxPanel = new JPanel();
        dialogueBoxPanel.setBackground(new Color(255, 0, 0));
        this.getContentPane().add(dialogueBoxPanel, BorderLayout.SOUTH);
        dialogueBoxPanel.setLayout(new CardLayout(5, 5));
            
        messageBox = new JTextPane();
        messageBox.setFont(new Font("Tahoma", Font.BOLD, 11));
        messageBox.setEditable(false);
        dialogueBoxPanel.add(messageBox);
    }
    
    
    /**
    * Nonogram puzzle. This create the nonogram puzzle
    */
    private void NonogramPuzzle() {
        Scanner fs = null;
        
        try {
            fs = new Scanner(new File(NGFILE));
        } catch (FileNotFoundException e) {
            messageBox.setText(NGFILE + " not found");
        }
        
        puzzle     = new Nonogram(fs);
    }
    
    
    /**
     * Make a move
     * 
     * @return the current move state
     */
    private char move() {
        if (moveState == FULL_CHAR) {
            moveState = EMPTY_CHAR;
            
            return FULL_CHAR;
        } else if (moveState == EMPTY_CHAR) {
            moveState = UNKNOWN_CHAR;
            
            return EMPTY_CHAR;
        } else {
            moveState = FULL_CHAR;
            
            return UNKNOWN_CHAR;
        }
    }
    
    
    /**
    * Get the user's move
    */
    private void getUserMove(char c, int row, int col) {
        int state = NonogramUI.stateFromChar(c);
        
        Assign userMove = new Assign(row, col, state);
        
        puzzle.setState(userMove);
    }
      
      
    public static void main(String[] args) {
        new NonogramGUI(); // create an instance of nonogram gui
    }
    
    
    private Scanner  scnr   = null;
    private Nonogram puzzle = null;
    
    private JTextPane messageBox = null;
    private char moveState = FULL_CHAR;
    
    private static final String NGFILE   = "nons/tiny.non";
    public static final char EMPTY_CHAR   = 'X';
    public static final char FULL_CHAR    = '@';
    public static final char UNKNOWN_CHAR = '.';
    public static final char INVALID_CHAR = '?';
    public static final char SOLVED_CHAR  = '*';
}
