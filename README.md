# TH_AndroidStudio buổi 2

## Gradle

    plugins {
        id 'com.android.application'
    }

    android {
      compileSdk 32

      defaultConfig {
          applicationId "com.example.th_4_13"
          minSdk 29
          targetSdk 32
          versionCode 1
          versionName "1.0"

          testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
      }

      buildTypes {
          release {
              minifyEnabled false
              proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
          }
      }
      compileOptions {
          sourceCompatibility JavaVersion.VERSION_1_8
          targetCompatibility JavaVersion.VERSION_1_8
      }
    }

    dependencies {

      implementation 'androidx.appcompat:appcompat:1.4.0'
      implementation 'com.google.android.material:material:1.6.1'
      implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
      implementation 'androidx.legacy:legacy-support-v4:1.0.0'
      testImplementation 'junit:junit:4.+'
      androidTestImplementation 'androidx.test.ext:junit:1.1.5'
      androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
    }

&nbsp;
## Tạo Bottom Navigation
---
- B1: Layout
    + Tạo menu navigation (icon, color)
    + main_ativity.xml: Tạo ViewPager + BottomNavigation
- B2: Tạo các class Fragment

        public class ProfileFragment extends Fragment {

            public ProfileFragment() {
                // Required empty public constructor
            }

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                    Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                return inflater.inflate(R.layout.fragment_profile, container, false);
            }
        }

- B3: Tạo ViewPagerFragmentAdapter

        public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {
            int pageNum;
            public ViewPagerFragmentAdapter(@NonNull FragmentManager fm, int behavior) {
                super(fm, behavior);
                this.pageNum = behavior;
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return new ListFragment();
                    case 1:
                        return new DashboardFragment();
                    case 2:
                        return new ProfileFragment();
                }
                return new ListFragment();
            }

            @Override
            public int getCount() {
                return pageNum;
            }
        }

- B4: trong class Main, lắng nghe sự kiện thay đổi menu bằng:

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_list:
                        bottomNavigation.getMenu().getItem(0).setCheckable(true);
                        viewPagerContainer.setCurrentItem(0);
                        return true;
                    case R.id.navigation_dashboard:
                        bottomNavigation.getMenu().getItem(1).setCheckable(true);
                        viewPagerContainer.setCurrentItem(1);
                        return true;
                    case R.id.navigation_profile:
                        bottomNavigation.getMenu().getItem(2).setCheckable(true);
                        viewPagerContainer.setCurrentItem(2);
                        return true;
                    default:
                        viewPagerContainer.setCurrentItem(0);
                        return true;
                }
            }
        });

&nbsp;
## Sử dụng RecylerView
---
- B1: Tạo file TaskListAdapter.class. 
Trong class TaskListAdapter:
    + Tạo lớp ViewHolder extends RecyclerView.ViewHolder (Dùng để truy cập, sửa đổi các thuộc tính của 1 item)

            public class ViewHolder extends RecyclerView.ViewHolder {
                TextView task_id, task_name, task_deadline,
                        task_content, task_status, task_colaboration;

                public ViewHolder(@NonNull View itemView) {
                    super(itemView);
                    task_id = itemView.findViewById(R.id.task_id);
                    task_name = itemView.findViewById(R.id.task_name);
                    task_deadline = itemView.findViewById(R.id.task_deadline);
                    task_content = itemView.findViewById(R.id.task_content);
                    task_status = itemView.findViewById(R.id.task_status);
                    task_colaboration = itemView.findViewById(R.id.task_colaboration);
                }
            }
    
    + Cho class TaskListAdapter extends __RecyclerView.Adapter<TaskListAdapter.ViewHolder>__

    + Khởi tạo biến:

            Context context;
            List<Task> mList;

            public TaskListAdapter(Context context, List<Task> mList) {
                this.context = context;
                this.mList = mList;
            }

    + Viết hàm getItemCount() => Trả về độ dài recyler sẽ hiển thị
    + Viết hàm onCreateViewHolder() => Khởi tạo layout cho item
    + Viết hàm onBindViewHolder() => Bind dữ liệu từ data sang layout để hiển thị

    ### Code hoàn chỉnh

        public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {
            Context context;
            List<Task> mList;

            public TaskListAdapter(Context context, List<Task> mList) {
                this.context = context;
                this.mList = mList;
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false);
                return new ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                Task task = mList.get(position);
                holder.task_id.setText(task.getId());
                holder.task_name.setText(task.getName());
                holder.task_deadline.setText(task.getDeadline());
                holder.task_content.setText(task.getContent());
                holder.task_status.setText(task.getStatus());
                holder.task_colaboration.setText(task.getColaboration() ? "Làm nhóm" : "Cá nhân");
            }

            @Override
            public int getItemCount() {
                return mList.size();
            }

            public class ViewHolder extends RecyclerView.ViewHolder {
                TextView task_id, task_name, task_deadline,
                        task_content, task_status, task_colaboration;

                public ViewHolder(@NonNull View itemView) {
                    super(itemView);
                    task_id = itemView.findViewById(R.id.task_id);
                    task_name = itemView.findViewById(R.id.task_name);
                    task_deadline = itemView.findViewById(R.id.task_deadline);
                    task_content = itemView.findViewById(R.id.task_content);
                    task_status = itemView.findViewById(R.id.task_status);
                    task_colaboration = itemView.findViewById(R.id.task_colaboration);
                }
            }
        }

- B2: Sử dụng RecylerView trong một Fragment

        public class ListFragment extends Fragment {
            TaskListAdapter taskListAdapter;
            List<Task> mList = new ArrayList<>();
            RecyclerView recyclerListTask;

            public ListFragment() {
                // Required empty public constructor
            }

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                    Bundle savedInstanceState) {
                // Inflate the layout for this fragment
                View view = inflater.inflate(R.layout.fragment_list, container, false);
                recyclerListTask = view.findViewById(R.id.recyclerListTask);

                try {
                    mList.add(new Task(1, "Nấu cơm", "Cần nấu cơm", "New",
                            "14/4/2023", false));
                    mList.add(new Task(2, "Nấu cơm 2", "Cần nấu cơm", "New",
                            "15/4/2023", false));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                        view.getContext(), LinearLayoutManager.VERTICAL, false);
                taskListAdapter = new TaskListAdapter(view.getContext(), mList);
                recyclerListTask.setLayoutManager(linearLayoutManager);
                recyclerListTask.setAdapter(taskListAdapter);
                return view;
            }
        }

&nbsp;
## Floating Button
---

